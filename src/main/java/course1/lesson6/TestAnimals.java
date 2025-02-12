package course1.lesson6;

public class TestAnimals {

    public static void main(String[] args) {
        Cat cat = new Cat("Барсик");
        System.out.println(cat.getName());
        cat.run(20);
        cat.run(600);
        cat.swim(20);
        cat.swim(600);
        System.out.println();

        Dog dog = new Dog("Тузик");
        System.out.println(dog.getName());
        dog.run(20);
        dog.run(600);
        dog.swim(10);
        dog.swim(600);
        System.out.println();

        System.out.println(Cat.countCats);
        System.out.println(Dog.countDog);
    }
}
