package course2.lesson5;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        firstMethod();
        System.out.println();
        secondMethod();
    }

    public static void firstMethod() {
        int size = 10_000_000;
        float[] arr = new float[size];
        Arrays.fill(arr,  (float) 1);
        long a = System.currentTimeMillis();

        for (int i = 0; i < size; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
                    Math.cos(0.4f + i / 2));
        }

        System.out.println("One thread time: " + (System.currentTimeMillis() - a) + " ms.");
    }

    public static void secondMethod() throws InterruptedException {
        int size = 10_000_000;
        float[] arr = new float[size];
        Arrays.fill(arr,  (float) 1);
        long startTime = System.currentTimeMillis();
        int half = size / 2;

        // Создаем два массива для левой и правой части исходного
        float[] arr1 = new float[half];
        float[] arr2 = new float[half];

        // Копируем в них значения из большого массива
        System.arraycopy(arr,0, arr1,0,half);
        System.arraycopy(arr, half, arr2, 0, half);

        // Запускает два потока и параллельно просчитываем каждый малый массив
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < arr1.length; i++) {
                arr1[i] = (float)(arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
                        Math.cos(0.4f + i / 2));
            }
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < arr2.length; i++) {
                arr2[i] = (float)(arr2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
                        Math.cos(0.4f + i / 2));
            }
        });
        thread2.start();
        thread1.join();
        thread2.join();

        // Склеиваем малые массивы обратно в один большой
        float[] resultArr = new float[size];
        System.arraycopy(arr1, 0, resultArr, 0, half);
        System.arraycopy(arr2, 0, resultArr, half,half);

        System.out.println("Two thread time: " + (System.currentTimeMillis() -
                startTime) + " ms.");
    }
}
