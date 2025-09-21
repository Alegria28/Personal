/*cspell: Ignore informacion */

// Librerías utilizadas en el programa
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.AbstractMap.SimpleEntry;

public class ValidAnagram {
    public static void main(String[] args) {
        System.out.println(isAnagram("aacc", "ccac"));
    }

    // Función que nosotros debemos crear
    public static boolean isAnagram(String s, String t) {

        // Si son de distinto tamaño, entonces estas palabras no son anagramas
        if (s.length() != t.length()) {
            return false;
        }

        // Creamos una lista para nuestra primera cadena
        List<Character> cadena1 = new ArrayList<>();
        // Creamos una lista para nuestra segunda cadena que tiene como información un
        // Character y un bool en cada espacio
        List<SimpleEntry<Character, Boolean>> cadena2 = new ArrayList<>();

        // Recorremos la primera cadena para crear nuestra lista
        for (int i = 0; i < s.length(); i++) {
            cadena1.add(s.charAt(i));
        }

        // Recorremos la segunda cadena para crear nuestra lista
        for (int i = 0; i < t.length(); i++) {
            // En nuestra lista, guardamos el carácter i de la segunda cadena y la información false en cada
            // espacio de la lista
            cadena2.add(new SimpleEntry<>(t.charAt(i), false));
        }

        // Variable bool para ver si encontramos nuestro carácter en la segunda cadena
        boolean encontrado = false;

        // Si tenemos una excepción aquí, entonces es un anagrama
        try {
            do {
                // Al buscar un nuevo carácter, la variable siempre inicia en false
                encontrado = false;

                // Recorremos nuestra segunda cadena
                for (int j = 0; j < t.length(); j++) {

                    // Obtenemos la información de la lista de nuestra segunda cadena en la posición j
                    SimpleEntry<Character, Boolean> informacion = cadena2.get(j);

                    if (cadena1.getFirst() == informacion.getKey() // Verificamos si encontramos este carácter en la
                                                                   // cadena 2
                            && !informacion.getValue()) { // y que ademas este no haya sido utilizado en una búsqueda
                                                          // anterior

                        // De ser asi entonces lo hemos encontrado
                        encontrado = true;
                        // Quitamos este carácter de nuestra primera lista
                        cadena1.removeFirst();
                        // Este carácter ya no vale para otra búsqueda en nuestra segunda lista
                        informacion = new SimpleEntry<>(informacion.getKey(), true);
                        // Guardamos la información actualizada en nuestra lista
                        cadena2.set(j, informacion);
                    }
                }

                // Si no hemos encontrado este carácter entonces ya sabemos que no es un
                // anagrama
                if (!encontrado) {
                    return false;
                }

            } while (encontrado);
        } catch (NoSuchElementException e) {
            return true;
        }

        return true;
    }
}