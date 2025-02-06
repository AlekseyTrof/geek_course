package course1.lesson6;

public class Cat extends Animal {
    public static int countCats;

    public Cat(String name) {
        super(name);
        countCats++;
    }

    @Override
    public void run(int distance) {
        if (distance > 200) {
            System.out.println("Слишком большое расстояние для бега. Больше 200 м.");
        } else {
            super.run(distance);
        }
    }

    @Override
    public void swim(int distance) {
        System.out.println("Кошки не умеют плавать");
    }
}
