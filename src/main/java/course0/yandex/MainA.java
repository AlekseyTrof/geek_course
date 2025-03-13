package course0.yandex;

import java.util.Arrays;
import java.util.Scanner;

public class MainA {
    private static final String CHARS = "abcdefghijklmnopqrstuvwxyz#";
    private static Scanner scanner = new Scanner(System.in);
    private static char[][] worlds;
    private static String[] numbers;
    private static int numberR;
    private static int numberC;

    public static String checkResult(char[][] worlds) {
        String result = null;
        char oneStr = 0;
        char twoStr = 0;

        int[] oneNum = new int[numberR];
        int[] twoNum = new int[numberC];

        for (int i = 0; i < numberR; i++) {
            for (int j = 0; j < CHARS.length(); j++) {
                if (worlds[i][0] == CHARS.charAt(j) && worlds[i][0] > 90) {
                    oneStr = worlds[i][0];
                    oneNum[i] = oneStr;
                }
            }
        }

        for (int i = 0; i < numberC; i++) {
            for (int j = 0; j < CHARS.length(); j++) {
                if (worlds[0][i] == CHARS.charAt(j) && worlds[0][i] > 90) {
                    twoStr = worlds[0][i];
                    twoNum[i] = twoStr;
                }
            }
        }

        int oneMin = oneNum[0];
        int twoMin = twoNum[0];

        for (int i = 1; i < numberR; i++) {
            if (oneMin > oneNum[i] && oneNum[i] > 90 && worlds[i][1] > 90) {
                oneMin = oneNum[i];
            }
        }

        for (int i = 1; i < numberC; i++) {
            if (twoMin > twoNum[i] && twoNum[i] > 90 && worlds[1][i] > 90) {
                twoMin = twoNum[i];
            }
        }

        if (oneMin < twoMin) {
            result = Character.toString(oneMin);
            int index = 0;
            for (int i = 0; i < numberR; i++) {
                if (worlds[i][0] == result.charAt(0) && worlds[i][0] > 90) {
                    index = i;
                }
            }
            for (int i = 1; i < numberC; i++) {
                String x = String.valueOf(worlds[index][i]);
                result = result.concat(x);
            }

        } else {
            result = Character.toString(twoMin);
            int index = 0;
            for (int i = 1; i < numberC; i++) {
                if (worlds[0][i] == result.charAt(0) && worlds[0][i] > 90) {
                    index = i;
                }
            }
            for (int i = 1; i < numberR; i++) {
                String x = String.valueOf(worlds[i][index]);
                result = result.concat(x);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        numbers = scanner.nextLine().split(" ");
        numberR = Integer.parseInt(numbers[0]);
        numberC = Integer.parseInt(numbers[1]);
        checkInputRC(numberR, numberC);
        worlds = new char[numberR][numberC];

        for (int i = 0; i < numberR; i++) {
            String world = scanner.nextLine();
            if (world.length() > numberC || world.length() < numberC) {
                world = scanner.nextLine();
            }
            for (int j = 0; j < world.length(); j++) {
                worlds[i][j] = world.charAt(j);
            }
        }
        String result = checkResult(worlds);
        System.out.println(result);
        scanner.close();

        for (int i = 0; i < numberR; i++) {
            System.out.println(Arrays.toString(worlds[i]));
        }

    }

    public static void checkInputRC(int numberR, int numberC) {
        while (numberR <= 1 || numberC >= 20) {
            numbers = scanner.nextLine().split(" ");
            numberR = Integer.parseInt(numbers[0]);
            numberC = Integer.parseInt(numbers[1]);

        }

    }
}
