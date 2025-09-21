#include <iostream>

using namespace std;

int main()
{
    int peso;

    cin >> peso;

    if (peso % 2 == 0 && peso != 2)
    {
        cout << "YES";
    }
    else
    {
        cout << "NO";
    }

    return 0;
}