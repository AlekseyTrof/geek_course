package course_1.lesson3;

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

        //Задание 4
        /*
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

    }


}
