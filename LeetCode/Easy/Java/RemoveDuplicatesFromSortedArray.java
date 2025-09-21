import java.util.*;

public class RemoveDuplicatesFromSortedArray {
    public static void main(String[] args) {

        int[] array = { 1, 1, 2 };

        int k = removeDuplicates(array);

        System.out.println(k);

    }

    public static int removeDuplicates(int[] nums) {

        // Creamos un setTree el cual nos deja acomodar los valores en orden ademas de
        // no dejar agregar valores repetidos
        Set<Integer> treeSet = new TreeSet<>();

        // Recorremos nuestro arreglo y agregamos valores a nuestro treeSet
        for (int numero : nums) {
            treeSet.add(numero);
        }

        // Recorremos nuestro arreglo y lo limpiamos (el valor a poner no importa)
        for (int i = 0; i < nums.length; i++) {
            nums[i] = (char) 'x';
        }

        // Recorremos nuestro treeSet y agregamos los números correspondientes al vector
        int indice = 0;
        for (int numero : treeSet) {
            nums[indice++] = numero;
        }

        // Retornamos el tamaño de nuestro treeSet (ya que este es el numero de números
        // distintos)
        return treeSet.size();

    }
}