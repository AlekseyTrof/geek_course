package course1.lesson3;

/*
Решение задачи в 3 домашке 8 задание
Сдвиг элементов в массиве
 */
public class ArrayShift {
    public static void main(String[] args) {
        int[] array1 = {1, 2, 3};
        int n1 = 1;
        shiftArray(array1, n1);
        printArray(array1); // Ожидается: [3, 1, 2]

        int[] array2 = {3, 5, 6, 1};
        int n2 = -21;
        shiftArray(array2, n2);
        printArray(array2); // Ожидается: [6, 1, 3, 5]
    }

    public static void shiftArray(int[] array, int n) {
        int length = array.length;
        if (length == 0) {
            return; // Если массив пустой, ничего не делаем
        }

        // Приводим n к положительному значению в пределах длины массива
        n = n % length;
        if (n < 0) {
            n += length; // Если n отрицательный, преобразуем его в положительный сдвиг
        }

        // Циклический сдвиг элементов
        reverse(array, 0, length - 1); // Разворачиваем весь массив
        reverse(array, 0, n - 1);       // Разворачиваем первые n элементов
        reverse(array, n, length - 1);  // Разворачиваем оставшиеся элементы
    }

    // Метод для разворота части массива
    private static void reverse(int[] array, int start, int end) {
        while (start < end) {
            int temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }

    // Метод для печати массива
    private static void printArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}