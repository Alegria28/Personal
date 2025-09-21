public class SignOfTheProductOfAnArray {
    public static void main(String[] args) {

        int[] vector = { -1, 1, -1, 1, -1 };

        System.out.println(arraySign(vector));
    }

    public static int arraySign(int[] nums) {

        // Contamos el numero de negativos que hay en el arreglo
        int totalNegativos = 0;
        for (int i = 0; i < nums.length; ++i) {

            // Si es 0, automáticamente todo el resultado lo es
            if (nums[i] == 0) {
                return 0;
            } else if (nums[i] < 0) {
                totalNegativos++;
            }
        }

        // Si el numero total de negativos es impar, debemos de retornar -1
        if (totalNegativos % 2 != 0) {
            return -1;
        } else {
            return 1;
        }
    }
}