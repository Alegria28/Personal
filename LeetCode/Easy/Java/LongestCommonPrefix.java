public class LongestCommonPrefix {
    public static void main(String[] args) {
        // Definimos un arreglo de cadenas de ejemplo
        String[] array = { "flower", "flow", "flight" };

        // Llamamos al método longestCommonPrefix y mostramos el resultado
        System.out.println(longestCommonPrefix(array));
    }

    public static String longestCommonPrefix(String[] strs) {
        // Usamos un StringBuilder para construir el prefijo común de manera eficiente
        StringBuilder resultado = new StringBuilder("");

        // Variable para controlar el índice de los caracteres que estamos comparando
        int i = 0;

        // Usamos un ciclo infinito para iterar mientras los caracteres sean iguales
        while (true) {
            // Bandera para determinar si todas las palabras tienen el mismo carácter en la
            // posición actual
            boolean iguales = true;

            // Verificamos si el índice actual está dentro del rango de la primera palabra
            // Si el índice excede la longitud de la primera palabra, terminamos el ciclo
            if (i >= strs[0].length()) {
                return resultado.toString();
            }

            // Obtenemos el carácter en la posición i de la primera palabra
            char comparador = strs[0].charAt(i);

            // Recorremos el resto de las palabras en el arreglo para comparar el carácter
            // en la posición i
            for (int j = 1; j < strs.length; j++) {
                // Verificamos dos condiciones:
                // 1. Si el índice i excede la longitud de la palabra actual (strs[j]),
                // significa que hemos llegado al final de esa palabra.
                // 2. Si el carácter en la posición i de la palabra actual (strs[j])
                // no coincide con el carácter de referencia (comparador).
                if (i >= strs[j].length() || strs[j].charAt(i) != comparador) {
                    // Si alguna de las condiciones se cumple, no hay coincidencia en esta posición.
                    // Cambiamos la bandera a false y salimos del ciclo.
                    iguales = false;
                    break;
                }
            }

            // Si todos los caracteres en la posición i son iguales
            if (iguales) {
                // Agregamos el carácter al prefijo común
                resultado.append(comparador);
                // Incrementamos el índice para comparar el siguiente carácter
                i++;
            } else {
                // Si encontramos una diferencia, terminamos el ciclo y retornamos el prefijo
                // común
                return resultado.toString();
            }
        }
    }
}