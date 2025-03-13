package course3.lesson6;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.println(numbersFourAndOne(1,2,3,4,5,6,7,8));
        System.out.println(numbersFourAndOne(1,2,3,5,6,7,8));
        System.out.println(numbersFourAndOne(2,3,4,5,6,7,8));
        System.out.println(numbersFourAndOne(2,3,5,6,7,8));
    }

    public static int[] array(int... integers) {
        if (integers.length == 0) {
            return new int[0];
        }
        long numberFour = Arrays.stream(integers).filter(integer -> integer == 4).count();
        if (numberFour == 0) {
            throw new RuntimeException("В массиве нет четверок");
        }
        int index = 0;
        for (int i = 0; i < integers.length; i++) {
            if (integers[i] == 4) {
                index = i + 1;
            }
        }
        return Arrays.stream(integers).skip(index).toArray();
    }

    public static boolean numbersFourAndOne(int... integers) {
        int countOne = 0;
        int countFour = 0;
        for (int integer : integers) {
            if (integer == 1) {
                countOne++;
            }
            if (integer == 4) {
                countFour++;
            }
        }
        if (countOne != 0 && countFour != 0) {
            return true;
        } else {
            return false;
        }
    }
}
