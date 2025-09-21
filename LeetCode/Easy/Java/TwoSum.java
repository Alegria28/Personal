// cspell: disable
public class TwoSum {

    public static void main(String[] args) {
        int[] arreglo = { 3, 3 };

        int[] nuevo = twoSum(arreglo, 6);

        System.out.print(nuevo[0]);
        System.out.print(nuevo[1]);
    }

    public static int[] twoSum(int[] nums, int target) {
        // Arreglo a retornar
        int[] resultado = new int[2];

        // Recorremos ambos arreglos para encontrar la suma
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = nums.length - 1; j >= 0; j--) {
                // Si la suma de estos indices es igual al resultado y la posición es distinta,
                // entonces guardamos los indices en el arreglo y lo retornamos
                if (nums[i] + nums[j] == target && i != j) {
                    resultado[0] = i;
                    resultado[1] = j;
                    return resultado;
                }
            }
        }
        return resultado;
    }

}
