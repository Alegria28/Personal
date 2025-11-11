#!/usr/bin/env bash
# install_arch_setup.sh
# Automated installer for a baseline Arch setup based on the user's package list.
# Safe, idempotent actions: installs packages via pacman and AUR via yay (builds yay if missing), enables services
# and performs a few safe config steps (fonts local.conf, xdg-user-dirs update, add user to docker group).

set -euo pipefail
IFS=$'\n\t'

# --- Configuration (edit here if needed) ---
PACMAN_PKGS=(
    xorg
    cinnamon
    lightdm
    lightdm-gtk-greeter
    networkmanager
    git
    nvim
    vi
    bash-completion
    base-devel
    # blueman
    xdg-user-dirs
    power-profiles-daemon
    gnome-keyring
    libsecret
    seahorse
    shutter
    nemo-fileroller
    reflector
    net-tools
    vlc
    vlc-plugin-ffmpeg
    shotwell
    nodejs
    npm
    redshift
    noto-fonts-emoji
    preload
    gnome-disk-utility
    kitty
)

# AUR packages (installed with yay). If a package exists in official repos, yay will install it too.
YAY_PKGS=(
    visual-studio-code-bin
    spotify
    onlyoffice-bin
    pyenv
    rofi-emoji
    docker
    postman-bin
    # add more AUR package names here if desired
)

# Fonts local.conf content (from the user's note)
read -r -d '' FONTS_LOCAL_CONF <<'EOF' || true
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE fontconfig SYSTEM "fonts.dtd">
<fontconfig>
  <alias>
    <family>sans-serif</family>
    <prefer>
      <family>Noto Color Emoji</family>
    </prefer>
  </alias>
  <alias>
    <family>serif</family>
    <prefer>
      <family>Noto Color Emoji</family>
    </prefer>
  </alias>
  <alias>
    <family>monospace</family>
    <prefer>
      <family>Noto Color Emoji</family>
    </prefer>
  </alias>
</fontconfig>
EOF

# --- End config ---

if [[ $(id -u) -ne 0 ]]; then
    echo "This script must be run as root (use sudo)."
    exit 1
fi

# Determine target non-root user to operate for user-specific tasks
TARGET_USER="${SUDO_USER:-${USER:-}}"
if [[ -z "$TARGET_USER" || "$TARGET_USER" == "root" ]]; then
    # Ask interactively if not available
    read -rp "Target non-root username for user-specific steps (e.g. your regular user): " TARGET_USER
    if [[ -z "$TARGET_USER" ]]; then
        echo "No target user provided. Exiting."
        exit 1
    fi
fi

info() { echo -e "[INFO] $*"; }
warn() { echo -e "[WARN] $*"; }
err() {
    echo -e "[ERROR] $*"
    exit 1
}

run_as_user() {
    local cmd="$*"
    if [[ "$TARGET_USER" == "root" ]]; then
        bash -c "$cmd"
    else
        sudo -u "$TARGET_USER" bash -c "$cmd"
    fi
}

install_pacman_packages() {
    info "Refreshing pacman DB and upgrading system (may ask for input)..."
    pacman -Syu --noconfirm || warn "pacman -Syu had issues; continuing"

    for pkg in "${PACMAN_PKGS[@]}"; do
        if pacman -Qs "^$pkg( |$)" >/dev/null 2>&1; then
            info "pacman: $pkg already installed; skipping"
        else
            info "Installing $pkg (pacman)"
            pacman -S --needed --noconfirm "$pkg" || warn "Failed to install $pkg via pacman"
        fi
    done
}

ensure_yay() {
    if command -v yay >/dev/null 2>&1; then
        info "yay found"
        return 0
    fi

    info "yay not found. Installing yay from AUR (will build as $TARGET_USER)."
    # ensure git and base-devel available
    pacman -S --needed --noconfirm git base-devel || warn "Could not install git/base-devel"

    tmpd=$(mktemp -d)
    chown "$TARGET_USER":"$TARGET_USER" "$tmpd" || true
    run_as_user "git clone https://aur.archlinux.org/yay.git '$tmpd/yay' && cd '$tmpd/yay' && makepkg -si --noconfirm"
    rm -rf "$tmpd"

    if ! command -v yay >/dev/null 2>&1; then
        warn "yay still not available after build. AUR package installs will be skipped."
        return 1
    fi
    return 0
}

install_yay_packages() {
    if [[ ${#YAY_PKGS[@]} -eq 0 ]]; then
        info "No AUR packages configured; skipping"
        return 0
    fi

    if ! command -v yay >/dev/null 2>&1; then
        ensure_yay || {
            warn "Skipping AUR packages because yay isn't available"
            return 0
        }
    fi

    for apkg in "${YAY_PKGS[@]}"; do
        if pacman -Qs "^$apkg( |$)" >/dev/null 2>&1 || yay -Qs "$apkg" >/dev/null 2>&1; then
            info "AUR: $apkg appears installed; skipping"
        else
            info "Installing AUR package: $apkg"
            run_as_user "yay -S --noconfirm --needed '$apkg'" || warn "Failed to install AUR package $apkg"
        fi
    done
}

enable_services_and_groups() {
    info "Enabling and starting common services"
    # network
    systemctl enable --now NetworkManager || warn "NetworkManager enable/start failed"
    # display manager
    systemctl enable --now lightdm || warn "lightdm enable/start failed"
    # bluetooth
    # systemctl enable --now bluetooth || warn "bluetooth service enable/start failed"
    # docker
    systemctl enable --now docker || warn "docker enable/start failed"
    # power profiles
    systemctl enable --now power-profiles-daemon || warn "power-profiles-daemon enable/start failed"

    # add user to docker group
    if id -nG "$TARGET_USER" | grep -qw docker; then
        info "$TARGET_USER already in docker group"
    else
        usermod -aG docker "$TARGET_USER" && info "Added $TARGET_USER to docker group"
    fi
}

write_fonts_local_conf() {
    local out="/etc/fonts/local.conf"
    if [[ -f "$out" ]]; then
        cp -v "$out" "${out}.backup.$(date +%s)" || warn "Could not backup existing local.conf"
    fi
    echo "Writing $out"
    printf "%s\n" "$FONTS_LOCAL_CONF" >"$out"
}

update_mirrors_with_reflector() {
    info "Updating mirrorlist with reflector (fetching 10 fastest mirrors)..."
    if ! command -v reflector >/dev/null 2>&1; then
        warn "reflector not found; skipping mirror update"
        return 0
    fi
    
    # Backup existing mirrorlist
    local mirrorlist="/etc/pacman.d/mirrorlist"
    if [[ -f "$mirrorlist" ]]; then
        cp -v "$mirrorlist" "${mirrorlist}.backup.$(date +%s)" || warn "Could not backup mirrorlist"
    fi
    
    # Update mirrors: get 10 fastest, recently synchronized, sort by rate
    reflector --latest 20 --protocol https --sort rate --save "$mirrorlist" --fastest 10 || warn "reflector failed to update mirrors"
    info "Mirrorlist updated successfully"
}

xdg_user_dirs() {
    info "Updating XDG user dirs for $TARGET_USER"
    run_as_user "xdg-user-dirs-update || true"
}

print_post_install_notes() {
    cat <<EOF

Post-install notes / manual steps:

- SDKMAN and Java/Gradle/Maven: install per-user via SDKMAN: 
  curl -s "https://get.sdkman.io" | bash
  Then run 'sdk install java', 'sdk install gradle', etc. This is per-user and not handled here.

- GNOME keyring: you may need to create a 'Login' keyring and enable automatic unlocking:
  See: https://wiki.archlinux.org/title/GNOME/Keyring
  The user's note mentioned removing ~/.local/share/keyrings/ to reset; don't do automatically here.

EOF
}

main() {
    install_pacman_packages
    update_mirrors_with_reflector
    # Ensure yay is available (build/install it) before attempting any AUR installs
    if ! ensure_yay; then
        warn "yay could not be installed; AUR package installation may be skipped"
    fi
    install_yay_packages
    enable_services_and_groups
    write_fonts_local_conf
    xdg_user_dirs
    print_post_install_notes
    info "Installation script finished. Some changes (like group membership) may require logout/login for the user $TARGET_USER."
}

main "$@"
