#include <iostream>

using namespace std;

int main()
{

    // Variables para recibir el tamaño del tablero
    int m = 0, n = 0;

    cin >> m >> n;

    // Calculamos el area de nuestro rectángulo
    int area = m * n;

    // Mientras que al area se le pueda restar 2 (el area del domino) y el area no termine negativa entonces aumentamos nuestro contador
    int contador = 0;
    while (1)
    {
        // Restamos al area
        area -= 2;

        if (area >= 0)
        {
            contador++;
            if (area == 0)
            {
                break;
            }
        }
        else
        {
            break;
        }
    }

    cout << contador;

    return 0;
}