package course2.lesson2;

public class Main {

    public static void main(String[] args) {
        String[][] array = new String[4][4];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = "2";
            }
        }
        //array[3][2] = "a";
        System.out.println(returnStrArray(array));
        System.out.println("\nСумма чисел в массиве = " + sumInArray(array));

    }

    public static String returnStrArray(String[][] array) {
        String result = "Array[4][4]";
        if (array.length == 4 && array[0].length == 4) {
            return result;
        }
        throw new MyArraySizeException("Массив не той длинны");
    }

    public static int sumInArray(String[][] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    int number = Integer.parseInt(array[i][j]);
                    sum += number;
                } catch (Exception e) {
                    throw new MyArrayDataException("Неверный формат числа: " + array[i][j] +
                            ". В ячейке array[" + i + "][" + j +"]");
                }
            }
        }
        return sum;
    }
}
