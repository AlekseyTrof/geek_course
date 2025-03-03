package course3.lesson1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Integer[] integers = {0, 1, 2, 3, 4, 5};
        String[] strings = {"0", "1", "2", "3", "4", "5"};

        changeElementsArray(integers, 0, 4);
        System.out.println(Arrays.toString(integers));

        changeElementsArray(strings, 0, 5);
        System.out.println(Arrays.toString(strings));


        List<Integer> list = listFromArray(integers);
        System.out.println(list);
    }

    public static <T> void changeElementsArray(T[] array, int index1, int index2) {
        T elementSave = array[index1];
        array[index1] = array[index2];
        array[index2] = elementSave;
    }

    public static <T> ArrayList<T> listFromArray(T[] array) {
        return new ArrayList<>(List.of(array));
    }
}
