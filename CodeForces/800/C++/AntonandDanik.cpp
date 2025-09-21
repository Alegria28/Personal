#include <iostream>
#include <string>

using namespace std;

int main()
{

    int numero = 0, anton = 0, danik = 0;

    cin >> numero;

    string juegos;

    cin >> juegos;

    for (int i = 0; i < numero; i++)
    {
        if (juegos[i] == 'A')
        {
            anton++;
        }
        else
        {
            danik++;
        }
    }

    if (anton > danik)
    {
        cout << "Anton";
    }
    else if (anton == danik)
    {
        cout << "Friendship";
    }
    else
    {
        cout << "Danik";
    }

    return 0;
}