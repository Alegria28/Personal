public class PalindromeNumber {

    public static void main(String[] args) {
        System.out.println(isPalindrome(10));
    }

    public static boolean isPalindrome(int x) {
        // Convertimos nuestro entero a un String
        String original = Integer.toString(x);
        // Convertimos nuestro entero a un StringBuilder (para después invertirlo)
        StringBuilder viceversa = new StringBuilder(Integer.toString(x));
        viceversa = viceversa.reverse();

        // Recorremos nuestros String verificando si los caracteres son iguales
        for (int i = 0; i < original.length(); i++){
            // Si no son iguales entonces mandamos un false
            if (!(original.charAt(i) == viceversa.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
