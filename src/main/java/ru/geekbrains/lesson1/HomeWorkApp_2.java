package ru.geekbrains.lesson1;

public class HomeWorkApp_2 {
    public static void main(String[] args) {
        amountLimits(3, 4);
        positiveOrNegative(30);
        negativeOrPositive(-23);
        printStrings("Привет, Моряк", 5);
        leapYear(2025);
    }

    public static boolean amountLimits(int a, int b) {
        /*
        Метод, принимающий на вход два целых числа и проверяющий, что их сумма лежит
        в пределах от 10 до 20 (включительно), если да – возвращает true, в противном случае – false.
         */
        int sum = a + b;
        if (sum > 10 && sum <= 20) {
            return true;
        } else {
            return false;
        }
    }

    public static void positiveOrNegative(int a) {
        /*
        Метод, которому в качестве параметра передается целое число, метод печатает в консоль,
        положительное ли число передали или отрицательное. Замечание: ноль считаем положительным числом.
         */
        if (a >= 0) {
            System.out.println("Число положительное");
        } else {
            System.out.println("Число отрицательное");
        }
    }

    public static boolean negativeOrPositive(int a) {
        /*
        Метод, которому в качестве параметра передается целое число. Метод
        возвращает true, если число отрицательное или false, если положительное.
         */
        if (a >= 0) {
            System.out.println("Число положительное");
            return false;
        } else {
            System.out.println("Число отрицательное");
            return true;
        }
    }

    public static void printStrings(String scr, int times) {
        /*
        Метод, которому в качестве аргументов передается строка и число, метод
        отпечатывает в консоль указанную строку, указанное количество раз
         */
        for (int i = 0; i < times; i++) {
            System.out.println(scr);
        }
    }

    public static boolean leapYear(int year) {
        /*
        Метод, который определяет, является ли год високосным, и возвращает boolean
        (високосный - true, не високосный - false)
         */
        System.out.println("Проверяем високосный год или нет: " + year);
        if (year >= 1584 &&
                year % 4 == 0 &&
                year != 1700 &&
                year != 1800 &&
                year != 1900 &&
                year != 2100 &&
                year != 2200 &&
                year != 2300) {
            System.out.println("Год високосный");
            return true;
        } else if (year < 1584) {
            // Год должен быть больше, чем 1584 (в котором был введен високосный год)
            // Если надо, можно вывести этот текст*
            System.out.println("Год не високосный");
            return false;
        } else {
            System.out.println("Год не високосный");
            return false;
        }
    }
}
