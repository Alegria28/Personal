#include <iostream>
#include <algorithm>

using namespace std;

int main()
{
    int t = 0;

    cin >> t;

    int a = 0, b = 0, contador = 0;
    ;

    for (int i = 0; i < t; i++)
    {
        cin >> a >> b;

        if ((a + b) % 2 == 0)
        {
            cout << "Bob\n";
        }
        else
        {
            cout << "Alice\n";
        }
    }

    return 0;
}