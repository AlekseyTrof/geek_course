package course3.lesson4;

public class NewThreeThreads {
    private static char letter = 'A';
    private static Object object = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (object) {
                try {
                    for (int i = 0; i < 5; i++) {
                        while (letter != 'A') {
                            object.wait();
                        }
                        System.out.print("A");
                        letter = 'B';
                        object.notifyAll();
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
        new Thread(() -> {
            synchronized (object) {
                try {
                    for (int i = 0; i < 5; i++) {
                        while (letter != 'B') {
                            object.wait();
                        }
                        System.out.print("B");
                        letter = 'C';
                        object.notifyAll();
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
        new Thread(() -> {
            synchronized (object) {
                try {
                    for (int i = 0; i < 5; i++) {
                        while (letter != 'C') {
                            object.wait();
                        }
                        System.out.println("C");
                        letter = 'A';
                        object.notifyAll();
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
