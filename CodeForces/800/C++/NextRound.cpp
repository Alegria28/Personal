#include <iostream>

using namespace std;

int main()
{

    // Numero de concursantes
    int concursantes = 0;
    cin >> concursantes;
    // Concursante con el cual vamos a comparar si los demás pasaron o no
    int numeroCorredor = 0;
    cin >> numeroCorredor;

    // Crear un vector dinámico para almacenar los pontajes
    int *puntajes = new int[concursantes];
    
    // Obtenemos los pontajes de los concursantes
    for (int i = 0; i < concursantes; i++)
    {
        cin >> puntajes[i];
    }

    // Contador para saber el numero de concursantes que pasaron
    int contador = 0;

    // Valor a comparar contra los demás
    int comparador = puntajes[numeroCorredor - 1];

    // Obtenemos los pontajes de los concursantes
    for (int i = 0; i < concursantes; i++)
    {
        if (puntajes[i] >= comparador && puntajes[i] != 0){
            contador++;
        }
    }

    cout << contador;

    return 0;
}