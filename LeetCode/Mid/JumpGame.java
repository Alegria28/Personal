public class JumpGame {
    public static void main(String[] args) {
        int[] vector = { 2, 3, 1, 1, 4 };

        System.out.println(canJump(vector));

    }

    public static boolean canJump(int[] nums) {

        // Tamaño máximo del vector (Iniciando a contar desde 0)
        int tamVector = nums.length - 1;

        // Si el tamaño del vector es 1, entonces siempre retorna true
        if (tamVector == 0) {
            return true;
        }

        // Iniciamos desde el primer indice
        int indice = 0;

        // Variable para saber el máximo alcance que tenemos al hacer el salto
        int maximoAlcance = 0;

        for (int i = indice; i < tamVector; i++) {
            if (i <= maximoAlcance) {

                maximoAlcance = Math.max(maximoAlcance, i + nums[i]);

                if (maximoAlcance >= tamVector) {
                    return true;
                }
            }
        }

        return false;
    }
}