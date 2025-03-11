package course3.lesson4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyExecutorService {
    private static ExecutorService threads = Executors.newFixedThreadPool(3);
    private static Object object = new Object();
    private static char currentLetter = 'A';

    public static void main(String[] args) {
        threads.execute(() -> {
            synchronized (object) {
                for (int i = 0; i < 100; i++) {
                    while (currentLetter != 'A') {
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.print("A");
                    currentLetter = 'B';
                    object.notifyAll();
                }
            }
        });
        threads.execute(() -> {
            synchronized (object) {
                for (int i = 0; i < 100; i++) {
                    while (currentLetter != 'B') {
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.print("B");
                    currentLetter = 'C';
                    object.notifyAll();
                }
            }
        });
        threads.execute(() -> {
            synchronized (object) {
                for (int i = 0; i < 100; i++) {
                    while (currentLetter != 'C') {
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("C");
                    currentLetter = 'A';
                    object.notifyAll();
                }
            }
        });
        threads.shutdown();
    }
}
