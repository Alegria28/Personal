// cspell: disable

public class MonotonicArray {
    public static void main(String[] args) {

        int[] vector = { 1, 3, 2 };

        System.out.println(isMonotonic(vector));
    }

    public static boolean isMonotonic(int[] nums) {

        // Un array de tamaño 1 siempre es true
        if (nums.length == 1) {
            return true;
        }

        // Banderas para saber si esta aumentando o disminuyendo
        boolean ascendente = true;
        boolean descendente = true;

        // Recorremos nuestro vector, iniciando desde el valor 1
        for (int i = 1; i < nums.length; ++i) {

            // Si ambas banderas son falsas, entonces no hay orden ascendente o descendente, por lo que
            // retornamos directamente false
            if (!ascendente && !descendente) {
                return false;
            }

            // Comparamos el valor actual contra el valor que esta entes de este
            if (nums[i] > nums[i - 1]) {
                descendente = false;
            }

            // Comparamos el valor actual contra el valor que esta entes de este
            if (nums[i] < nums[i - 1]) {
                ascendente = false;
            }
        }

        return ascendente || descendente;
    }
}