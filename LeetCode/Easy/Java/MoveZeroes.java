public class MoveZeroes {
    public static void main(String[] args) {

        int[] vectorPrueba = { 0, 1, 0, 3, 12 };

        moveZeroes(vectorPrueba);

        for (int i = 0; i < vectorPrueba.length; ++i) {
            System.out.print("[" + vectorPrueba[i] + "]");
        }

    }

    public static void moveZeroes(int[] nums) {

        // Creamos un vector del mismo tamaño del que nos llego
        int[] vectorCorrecto = new int[nums.length];

        // Copiamos los valores a este nuevo vector ignorando los ceros
        int j = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != 0) {
                vectorCorrecto[j] = nums[i];
                j++;
            }
        }

        // Sobrescribimos los valores correctos al vector
        for (int i = 0; i < vectorCorrecto.length; ++i) {
            nums[i] = vectorCorrecto[i];
        }

    }
}