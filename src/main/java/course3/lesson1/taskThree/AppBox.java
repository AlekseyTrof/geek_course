package course3.lesson1.taskThree;

import com.sun.jdi.ObjectReference;

public class AppBox {
    public static void main(String[] args) {

        Box<Fruit> box1 = new Box<>();
        box1.addAllFruit(new Apple(), new Apple());
        box1.printFruits();
        System.out.println("Общий вес коробки: " + box1.getWeightAll());
        System.out.println();

        Box<Fruit> box2 = new Box<>();
        box2.addAllFruit(new Orange(), new Orange());
        box2.printFruits();
        System.out.println("Общий вес коробки: " + box2.getWeightAll());
        System.out.println();

        box1.deleteAllInBox();
        box2.pour(box1);
        box1.printFruits();
        System.out.println("Общий вес коробки: " + box1.getWeightAll());
        box2.printFruits();
    }
}
