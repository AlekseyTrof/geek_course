package course3.lesson4;

public class ThreeThreads {
    private static char currentLetter = 'A';
    private static final Object object = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread A = new Thread(new A());
        Thread B = new Thread(new B());
        Thread C = new Thread(new C());
        B.start();
        C.start();
        A.start();
    }

    public static class A implements Runnable {
        @Override
        public void run() {
            synchronized (object) {
                for (int i = 0; i < 10; i++) {
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
        }
    }

    public static class B implements Runnable {
        @Override
        public void run() {
            synchronized (object) {
                for (int i = 0; i < 10; i++) {
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
        }
    }

    public static class C implements Runnable {
        @Override
        public void run() {
            synchronized (object) {
                for (int i = 0; i < 10; i++) {
                    while (currentLetter != 'C') {
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.print("C" + "\n");
                    currentLetter = 'A';
                    object.notifyAll();
                }
            }
        }
    }
}
