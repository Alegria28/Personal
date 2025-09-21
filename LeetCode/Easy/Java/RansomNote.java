public class RansomNote {
    public static void main(String[] args) {
        System.out.print(canConstruct("aa", "ab"));
    }

    public static boolean canConstruct(String ransomNote, String magazine) {

        boolean bandera = false;

        char[] vector = new char[magazine.length()];

        for (int i = 0; i < magazine.length(); i++) {
            vector[i] = magazine.charAt(i);
        }

        // Vamos a recorrer nuestra primera cadena
        for (int i = 0; i < ransomNote.length(); i++) {
            bandera = false;
            // Recorremos el vector que representa la segunda cadena
            for (int j = 0; j < magazine.length(); j++) {
                // Verificamos si encontramos el carácter i de la primera cadena en algún
                // carácter del vector que representa la segunda cadena
                if (ransomNote.charAt(i) == vector[j]) {

                    // Para que no vuelva a tomar este valor para futuras comparaciones, modificamos
                    // su valor
                    vector[j] = '.';

                    // Si lo encontramos entonces ya no comparamos y la bandera cambia su estado
                    bandera = true;
                    break;
                }
            }

            // Si tras recorrer todo el vector de la segunda cadena la bandera sigue en
            // falso, significa que no se encontró ese carácter
            if (!bandera) {
                return false;
            }

        }

        return true;
    }
}