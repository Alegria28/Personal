#include <iostream>
#include <string.h>

using namespace std;

int main()
{

    char palabra[101];

    cin >> palabra;

    int tam = strlen(palabra);

    int mayusculas = 0, minusculas = 0;

    for (int i = 0; i < tam; i++)
    {
        if (isupper(palabra[i]))
        {
            mayusculas++;
        }
        else
        {
            minusculas++;
        }
    }

    char *aux;

    aux = new char[tam];

    aux[tam + 1] = '\0';

    for (int i = 0; i < tam; i++)
    {
        if (mayusculas > minusculas)
        {
            palabra[i] = toupper(palabra[i]);
        }
        else
        {
            palabra[i] = tolower(palabra[i]);
        }
    }

    for (int i = 0; i < tam; i++)
    {
        cout << palabra[i];
    }

    return 0;
}