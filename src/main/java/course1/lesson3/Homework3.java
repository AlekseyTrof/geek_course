package course1.lesson3;

import java.util.Arrays;

public class Homework3 {
    public static void main(String[] args) {
        //Задание 1.
        //Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [1, 1, 0, 0, 1, 0, 1, 1,
        //0, 0]. С помощью цикла и условия заменить 0 на 1, 1 на 0
        System.out.println("Задание 1:");
        int[] arr = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                arr[i] = 1;
            } else {
                arr[i] = 0;
            }
        }
        System.out.println(Arrays.toString(arr));
        System.out.println();

        //Задание 2.
        //Задать пустой целочисленный массив длиной 100. С помощью цикла заполнить его
        //значениями 1 2 3 4 5 6 7 8 … 100
        System.out.println("Задание 2:");
        int[] arr2 = new int[100];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = i + 1;
        }
        System.out.println(Arrays.toString(arr2));
        System.out.println();

        //Задание 3.
        //Задать массив [1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1] пройти по нему циклом, и числа меньшие 6
        //умножить на 2
        System.out.println("Задание 3:");
        int[] arr3 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < arr3.length; i++) {
            if (arr3[i] < 6) {
                arr3[i] *= 2;
            }
        }
        System.out.println(Arrays.toString(arr3));
        System.out.println();

        /*
        Задание 4.
        Создать квадратный двумерный целочисленный массив (количество строк и столбцов
        одинаковое), и с помощью цикла(-ов) заполнить его диагональные элементы единицами
        (можно только одну из диагоналей, если обе сложно).
         */
        System.out.println("Задание 4:");
        int[][] arr4 = new int[10][10];
        for (int i = 0; i < arr4.length; i++) {
            for (int j = 0; j < arr4.length; j++) {
                if (i == j) {
                    arr4[i][j] = 1;
                }
                if (i + j == 9) {
                    arr4[i][j] = 1;
                }
            }
        }
        for (int i = 0; i < arr4.length; i++) {
            System.out.println(Arrays.toString(arr4[i]));
        }
        System.out.println();

        /*
        Задание 5.
        Написать метод, принимающий на вход два аргумента: len и initialValue, и возвращающий
        одномерный массив типа int длиной len, каждая ячейка которого равна initialValue
        */
        System.out.println("Task 5:");
        int[] array1 = array(5, 10);
        System.out.println(Arrays.toString(array1));
        System.out.println();

        /*
        Task 6
        Задать одномерный массив и найти в нем минимальный и максимальный элементы
         */
        System.out.println("Task 6:");
        int[] arrayMaxMin = {2, 3, 6, 7, 9, 2, 5, 8, 9, 0, 1, -5, 5, 11};
        System.out.println(Arrays.stream(arrayMaxMin).max());
        System.out.println(Arrays.stream(arrayMaxMin).min());
        //Через свой метод:
        System.out.println(arrayMin(arrayMaxMin));
        System.out.println(arrayMax(arrayMaxMin));
        System.out.println();

        /*
        Task 7
        Написать метод, в который передается не пустой одномерный целочисленный массив,
        метод должен вернуть true, если в массиве есть место, в котором сумма левой и правой части массива равны.
        Примеры:
        checkBalance([2, 2, 2, 1, 2, 2, ||| 10, 1]) → true, т.е. 2 + 2 + 2 + 1 + 2 + 2 = 10 + 1
        checkBalance([1, 1, 1, ||| 2, 1]) → true, т.е. 1 + 1 + 1 = 2 + 1
         */
        System.out.println("Task 7:");
        int[] arrayBalance = {2, 2, 2, 1, 2, 2, 10, 1};
        System.out.println(checkBalance(arrayBalance));
        System.out.println();

        /*
        Написать метод, которому на вход подается одномерный массив и число n (может быть положительным
        или отрицательным), при этом метод должен сместить все элементы массива на n позиций.
        Элементы смещаются циклично. Для усложнения задачи нельзя пользоваться вспомогательными массивами.
        Примеры: [1, 2, 3] при n = 1 (на один вправо) -> [3, 1, 2],
        [3, 5, 6, 1] при n = -2 (на два влево) -> [6, 1, 3, 5]. При каком n в какую сторону сдвига можете выбирать сами.
         */
        System.out.println("Task 8:");
        int[] arrayShift = {1, 2, 3, 4, 5};
        //решение в отдельном классе

    }

    //Implementation of task 8:
    public static void arrayShifting(int[] array, int shift) {

    }

    //Implementation of task 7:
    public static boolean checkBalance(int[] checkBalance) {
        boolean balance = false;
        if (checkBalance.length == 0 || checkBalance.length == 1) {
            return false;
        }
        int index = 0;
        int sum1 = checkBalance[index];
        int sum2 = 0;
        while (index < checkBalance.length) {
            for (int i = index + 1; i < checkBalance.length; i++) {
                sum2 += checkBalance[i];
            }
            if (sum1 == sum2) {
                balance = true;
            }
            index++;
            if (index == checkBalance.length) {
                break;
            }
            sum2 = 0;
            sum1 += checkBalance[index];
        }
        return balance;
    }

    //Implementation of task 5:
    public static int[] array(int len, int initialValue) {
        int[] array = new int[len];
        Arrays.fill(array, initialValue);
        return array;
    }

    //Implementation of task 6:
    public static int arrayMin(int[] array) {
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    public static int arrayMax(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }
}
