public class PlusOne {
    public static void main(String[] args) {

        int[] vector = { 7, 2, 8, 5, 0, 9, 1, 2, 9, 5, 3, 6, 6, 7, 3, 2, 8, 4, 3, 7, 9, 5, 7, 7, 4, 7, 4, 9, 4, 7, 0, 1,
                1, 1, 7, 4, 0, 0, 6 };

        int[] prueba = plusOne(vector);

        // Mostramos el resultado
        for (int i = 0; i < prueba.length; ++i) {

            System.out.print("[" + prueba[i] + "]");
        }

    }

    public static int[] plusOne(int[] digits) {

        // Obtenemos el tamaño del vector
        int tam = digits.length;

        // Recorremos el vector, iniciando desde el ultimo dígito
        for (int i = digits.length - 1; i >= 0; --i) {
            // Si el dígito es menor a 9, simplemente le sumamos 1
            if (digits[i] < 9) {
                digits[i]++;
                // Hemos completado la operación, entonces retornamos el vector
                return digits;
            }
            // Si el ultimo dígito es 9, entonces pasa a 0
            else {
                digits[i] = 0;
            }
        }

        // Si hemos llegado aquí, entonces todos los dígitos eran 9 (999 -> 1000)
        int[] resultado = new int[tam + 1];
        resultado[0] = 1;

        return resultado;
    }
}