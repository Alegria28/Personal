/*cspell: ignore */

// Librerías que se usaron en el programa
import java.util.LinkedList;
import java.util.List;

class FindtheIndexoftheFirstOccurrenceinaString {

    public static void main(String[] args) {
        System.out.println(strStr("mississippi", "issip"));

    }

    public static int strStr(String haystack, String needle) {

        // En caso de que needle sea mas grande que haystack entonces no hacemos nada y
        // regresamos -1
        if (needle.length() > haystack.length()) {
            return -1;
        }

        // Variable que vamos a regresar
        int resultado = 0;

        // Variable para almacenar el numero de veces que encontremos una letra de
        // needle en haystack
        int contador = 0;

        // Para que sea mas sencilla la búsqueda, convertiremos needle a una lista
        // enlazada
        List<Character> needleLista = new LinkedList<>();
        // Recorremos el string para crear la lista
        for (int i = 0; i < needle.length(); i++) {
            needleLista.add(needle.charAt(i));
        }

        // Creamos una constante que nos sera util mas adelante (con lo que tiene
        // needleLista)
        final List<Character> needleListaBase = new LinkedList<>(needleLista);

        // El ciclo for lo controla la variable haystack (ya que vamos a recorrer
        // este
        // String por completo hasta que encontremos la coincidencia de needle en
        // haystack)
        for (int i = 0; i < haystack.length(); i++) {

            // Si mientras recorremos la cadena encontramos una coincidencia
            if (haystack.charAt(i) == needleLista.getFirst()) {
                // Si es la primera letra que encontramos, entonces guardamos el indice de
                // este
                if (contador == 0) {
                    resultado = i;
                }

                // Sumamos al contador (que al final debe de ser igual al tamaño de needle)
                contador++;
                // Ya no nos sirve el primer elemento de la lista, por lo que lo quitamos
                needleLista.removeFirst();

                // Si al contar, vemos que hemos encontrado toda la palabra (que contador sea
                // igual al tamaño de needle), entonces hemos terminado
                if (contador == needle.length()) {
                    // Si lo hemos encontrado, no hace falta que lo busquemos a la inversa
                    return resultado;
                }

            }
            // De no ser una coincidencia, entonces reiniciamos el contador y nuestra lista
            // de nuevo
            else {
                // Primero la limpiamos para después asignar valores de nuevo
                needleLista.clear();
                needleLista.addAll(needleListaBase);
                resultado = -1;
                contador = 0;
            }
        }

        return -1;
    }
}