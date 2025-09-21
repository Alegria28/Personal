// Eduardo Alegria
// https://codeforces.com/problemset/problem/71/A
// cspell:
#include <iostream>
#include <string>

#define MAX 10

using namespace std;

int main()
{

    // Variable para numero de casos
    int casos;
    cin >> casos;

    // For para numero de casos
    for (int i = 0; i < casos; i++)
    {
        // Recibimos nuestra cadena
        string cadena;
        cin >> cadena;

        // Lo primero es verificar si nuestra cadena tiene menos de 10 caracteres
        if (cadena.length() <= MAX)
        {
            cout << cadena << endl;
            // De ser asi entonces ignoramos todo lo que sigue en el for
            continue;
        }

        // Si hemos llegado a este punto es porque nuestra palabra tiene mas de 10 caracteres por lo que debemos de convertirla
        int contador = 0;

        // Imprimimos la primera letra
        cout << cadena[0];
        // Recorremos nuestra palabra (ignorando la primera y ultima letra de esta)
        for (int i = 1; i < cadena.length() - 1; i++)
        {
            // Contamos el numero de caracteres
            contador++;
        }

        // Imprimimos el total de letras encontradas
        cout << to_string(contador);

        // Imprimimos la ultima letra
        cout << cadena[cadena.length() - 1];

        cout << endl;
    }

    return 0;
}