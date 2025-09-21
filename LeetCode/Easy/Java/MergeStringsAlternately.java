import java.util.LinkedList;
import java.util.List;

class MergeStringsAlternately {
    public static void main(String[] args) {

        System.out.println(mergeAlternately("abcd", "pq"));
    }

    public static String mergeAlternately(String word1, String word2) {
        // Variable resultado
        String resultado = "";

        // Creamos una lista simple para cada String donde cada nodo representa un
        // caracter de la cadena
        List<Character> list1 = new LinkedList<>();
        for (int i = 0; i < word1.length(); i++) {
            list1.add(word1.charAt(i));
        }
        List<Character> list2 = new LinkedList<>();
        for (int i = 0; i < word2.length(); i++) {
            list2.add(word2.charAt(i));
        }

        // Dado el problema, el ciclo for lo controla el total de elementos de las 2
        // cadenas
        for (int i = 0; i < word1.length() + word2.length(); i++) {

            // Siempre al iniciar se iniciara con el primer elemento de la cadena 1
            if (i == 0) {
                // Agregamos esta caracter al resultado
                resultado += list1.getFirst();
                // Ya que lo utilizamos, entonces quitamos este elemento
                list1.removeFirst();
            }

            // Segun observamos, hay un patron donde si i es impar agregamos un elemento el
            // String word2 y si no entonces agrega un elemento del String word1 (aun asi no
            // entiendo porque en codigo esto funciona al reves)
            if (i % 2 == 0) {
                // Si ya hemos vaciado word2 entonces paramos el ciclo
                if (list2.isEmpty()) {
                    break;
                } else {
                    // Agregamos este caracter al resultado
                    resultado += list2.getFirst();
                    // Ya que lo utilizamos, entonces quitamos este caracter
                    list2.removeFirst();
                }
            } else {
                // Si ya hemos vaciado word1 entonces paramos el ciclo
                if (list1.isEmpty()) {
                    break;
                } else {
                    // Agregamos este caracter al resultado
                    resultado += list1.getFirst();
                    // Ya que lo utilizamos, entonces quitamos este caracter
                    list1.removeFirst();
                }
            }
        }

        // Segun cual lista haya quedado vacia, agregamos los caracteres que sobraron
        if (list1.isEmpty() && list2.isEmpty()) {
            return resultado;
        } else if (list1.isEmpty()) {
            // Ya que la lista 1 esta vacia, pudieron haber quedado elementos sobrantes en
            // la lista 2, por lo que los agregaremos a nuestra variable resultado
            for (Character character : list2) {
                resultado += character;
            }
        } else if (list2.isEmpty()) {
            // Ya que la lista 1 esta vacia, pudieron haber quedado elementos sobrantes en
            // la lista 2, por lo que los agregaremos a nuestra variable resultado
            for (Character character : list1) {
                resultado += character;
            }
        }

        return resultado;
    }
}