#include <iostream>
#include <string.h>

using namespace std;

int main()
{

    char palabra[1001];

    cin >> palabra;

    int tam = strlen(palabra);

    palabra[0] = toupper(palabra[0]);

    for (int i = 0; i < tam; i++)
    {
        cout << palabra[i];
    }

    return 0;
}