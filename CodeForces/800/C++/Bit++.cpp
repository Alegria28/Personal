#include <iostream>
#include <string>

using namespace std;

int main()
{

    // Valor inicial de nuestra variable
    int x = 0;

    // Numero de operaciones a realizar
    int total = 0;
    cin >> total;

    for (int i = 0; i < total; i++)
    {
        string input = "";
        cin >> input;
        
        // Si al buscar "++" esto es distinto de npos (no hay posición, osea que no esta en el string) entonces hacemos la operación
        if(input.find("++") != string::npos){
            x++;
        }else if(input.find("--") != string::npos){
            x--;
        }

    }
    cout << x;

    return 0;
}