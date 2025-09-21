#include <iostream>

using namespace std;

int main()
{

    int matriz[5][5];

    int x = 0, y = 0;

    for (int i = 0; i < 5; i++)
    {
        for (int j = 0; j < 5; j++)
        {
            cin >> matriz[i][j];
            if (matriz[i][j] == 1)
            {
                x = i + 1;
                y = j + 1;
            }
        }
    }

    int xx = abs(x - 3);
    int yy = abs(y - 3);

    cout << xx + yy;

    return 0;
}