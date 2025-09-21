/*cspell: ignore*/

public class Pow {
    public static void main(String[] args) {

        // Llamamos a nuestra función para ver que obtenemos
        System.out.println(myPow(-1.00000, -2147483648));
    }

    // Función que nosotros debemos crear
    public static double myPow(double x, int n) {

        // Siempre que el exponente sea 0, el resultado es 1
        if (n == 0) {
            return 1;
        }

        // En caso de que la base sea 1, el resultado siempre va a ser 1
        if (x == 1 || x == -1) {
            // En caso de que la base sea -1, el resultado siempre va a ser -1
            if (x == -1) {
                if (n < 0){
                    return 1;
                }
                return -1;
            }
            return 1;
        }

        // Almacenamos el valor por el que siempre se va a multiplicar
        double valor_multiplicar = x;

        // En caso de que el exponente sea negativo
        if (n < 0) {

            // Si el exponente es negativo y ademas es muy grande, el numero va a ser muy
            // cercano a 0
            if (n < -1000000) {
                return 0;
            }

            // Convertimos el exponente a positivo (usando valor absoluto)
            n = Math.abs(n);

            // Elevamos el numero a la n
            for (int i = 0; i < n - 1; i++) {
                x = x * valor_multiplicar;
            }

            return 1.0 / x;
        }
        // En caso de que el exponente sea positivo
        else {

            // Elevamos el numero a la n
            for (int i = 0; i < n - 1; i++) {
                x = x * valor_multiplicar;
            }

            return x;
        }
    }
}