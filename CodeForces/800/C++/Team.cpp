#include <iostream>

using namespace std;

int main()
{
    // Variable que representara la cantidad de problemas que va a poder resolver el equipo
    int resueltos = 0;
    // Variable que representara la cantidad de problemas a realizar por el equipo
    int problemas = 0;
    cin >> problemas;

    for (int i = 0; i < problemas; i++)
    {
        // Reiniciamos nuestro contador cada vez que iniciamos un problema
        int contador = 0;
        for (int j = 0; j < 3; j++)
        {
            // Recibimos ya sea 0/1
            int respuesta = 0;
            cin >> respuesta;

            // Aumentamos nuestro contador si nuestra respuesta es 1
            if (respuesta == 1)
            {
                contador++;
            }
        }

        // Si nuestro contador es mayor o igual a 2, entonces podemos resolver el problema
        if (contador >= 2)
        {
            resueltos++;
        }
    }

    // Tras finalizar mostramos la cantidad de problemas que el equipo pudo resolver
    cout << resueltos;

    return 0;
}