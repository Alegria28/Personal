#include <iostream>

using namespace std;

int main()
{

    int a, b, contador;

    cin >> a >> b;

    while (a <= b)
    {
        if (a > b)
        {
            break;
        }
        else
        {
            a *= 3;
            b *= 2;
            contador++;
        }
    }

    cout << contador;

    return 0;
}