#include <iostream>

using namespace std;

int main()
{
    int n, h, salida, altura;

    cin >> n >> h;

    // Pedimos el numero de amigos y la altura de la barda
    for (int i = 0; i < n; i++)
    {
        cin >> altura;

        if (altura > h)
        {
            salida += 2;
        }
        else
        {
            salida++;
        }
    }

    cout << salida;

    return 0;
}