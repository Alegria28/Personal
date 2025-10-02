# cspell: disable
#!/bin/bash

# Detenemos el script si un comando falla
set -e

# ---- Variables ----
USERNAME="Alegria"
HOSTNAME="Alegria-Laptop"
TIMEZONE="America/Mexico_City"
LOCALE_LANG="en_US.UTF-8"
KEYMAP="us"

echo ":: Sincronizando repositorios y actualizando el sistema..."
pacman -Syu --noconfirm

echo ":: Configurando zona horaria..."
ln -sf /usr/share/zoneinfo/${TIMEZONE} /etc/localtime
# Sincronizar el reloj del hardware con el del sistema
hwclock --systohc

echo ":: Localizacion e idioma..."
# Descomentamos el locale que necesitamos
sed -i "s/^#${LOCALE_LANG}/${LOCALE_LANG}/" /etc/locale.gen
# Generamos los locales
locale-gen

echo ":: Configurando lenguaje del sistema..."
echo "LANG=${LOCALE_LANG}" > /etc/locale.conf

echo ":: Configurando teclado de la consola..."
echo "KEYMAP=${KEYMAP}" > /etc/vconsole.conf

echo ":: Configurando nombre del equipo..."
hostnamectl set-hostname "$HOSTNAME"

echo ":: Configurando archivo hosts..."
cat > /etc/hosts <<EOF
127.0.0.1   localhost
::1         localhost
127.0.1.1   ${HOSTNAME}.localdomain   ${HOSTNAME}
EOF 

echo ":: Estableciendo la contraseña de root..."
passwd

echo ":: Instalando GRUB..."
pacman -S --noconfirm grub efibootmgr

echo "A continuación se muestra la disposición de los discos:"
lsblk

# Creamos una variable vacía para el disco
DISK=""
# Pedimos al usuario que introduzca el disco y lo guardamos en la variable DISK
read -p "Por favor, introduce el nombre del disco de arranque (ej: /dev/sda o /dev/nvme0n1): " DISK

echo ":: Instalando GRUB en el disco seleccionado: ${DISK}..."
# Usamos la variable $DISK para indicar a GRUB dónde instalarse.
grub-install --target=x86_64-efi --efi-directory=/boot --bootloader-id=GRUB "$DISK"

echo ":: Generando archivo de configuración de GRUB..."
grub-mkconfig -o /boot/grub/grub.cfg

echo ":: Creando usuario no-root..."
useradd -m -G wheel "$USERNAME"

echo ":: Estableciendo contraseña para $USERNAME..."
passwd "$USERNAME"

echo ":: Configurando permisos de sudo para el grupo 'wheel'..."
sed -i 's/^# %wheel ALL=(ALL:ALL) ALL/%wheel ALL=(ALL:ALL) ALL/' /etc/sudoers

echo ":: Instalando paquetes..."
pacman -S --noconfirm \
    xorg-server \
    pipewire pipewire-pulse pipewire-jack \
    bash-completion \
    openssh \
    zip unzip \
    vlc \
    docker

echo ":: Instalando drivers de video (Intel)"
pacman -S --noconfirm xf86-video-intel vulkan-intel

echo ":: Habilitando servicios..."
systemctl enable NetworkManager
systemctl enable docker

echo ":: Instalando yay para el usuario $USERNAME..."
# Nos movemos al directorio home del nuevo usuario para trabajar.
cd "/home/$USERNAME"
# Creamos un directorio para las compilaciones del AUR.
mkdir -p "AUR"
chown -R "$USERNAME:$USERNAME" "AUR"

# Clonamos el repositorio de yay y lo instalamos como el usuario no-root.
# Esto es CRUCIAL para la seguridad y el correcto funcionamiento.
sudo -u "$USERNAME" bash -c '
    set -e
    cd /home/'"$USERNAME"'/AUR
    git clone https://aur.archlinux.org/yay.git
    cd yay
    makepkg -si --noconfirm