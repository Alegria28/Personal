import java.util.*;

public class FizzBuzz {
    public static void main(String[] args) {

        int n = 15;

        List<String> resultado = fizzBuzz(n);

        for (int i = 0; i < n; i++) {
            System.out.print("[" + resultado.get(i) + "]");
        }

    }

    public static List<String> fizzBuzz(int n) {
        // Lista a retornar
        List<String> resultado = new ArrayList<>();

        // Utilizamos un ciclo for
        for (int i = 1; i <= n; i++) {
            // Si nuestro numero i es dividido entre 3 y 5
            if (i % 5 == 0 && i % 3 == 0) {
                // Si esto es cierto, entonces agregamos "FizzBuzz"
                resultado.add("FizzBuzz");
            } else if (i % 3 == 0) {
                // Si esto es cierto, entonces agregamos "Fizz"
                resultado.add("Fizz");
            } else if (i % 5 == 0) {
                // Si esto es cierto, entonces agregamos "Buzz"
                resultado.add("Buzz");
            } else {
                // Si ninguno de estos casos es correcto entonces almacenamos i como String
                resultado.add(Integer.toString(i));
            }
        }

        return resultado;
    }
}