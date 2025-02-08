package cource1.lesson6;

public class Dog extends Animal {
    public static int countDog;

    public Dog(String name) {
        super(name);
        countDog++;
    }

    @Override
    public void run(int distance) {
        if (distance > 500) {
            System.out.println("Слишком большое расстояние для бега. Больше 500 м.");
        } else {
            super.run(distance);
        }
    }

    @Override
    public void swim(int distance) {
        if (distance > 10) {
            System.out.println("Слишком большое расстояние для плаванья. Больше 10 м.");
        } else {
            super.swim(distance);
        }
    }
}
