package course3.lesson1.taskThree;

import java.util.ArrayList;

public class Box <T extends Fruit> {
    private T obj;
    private ArrayList<T> box;
    private double weight = 0.0;

    public Box() {
        box = new ArrayList<>();
    }

    public double getWeightAll() {
        if (obj == null && box.isEmpty()) {
            return weight;
        } else {
            weight = box.size() * obj.getWeight();
        }
        return weight;
    }

    public void addFruit(T fruit) {
        if (box.isEmpty()) {
            obj = fruit;
        }
        if (fruit.getClass() == obj.getClass()) {
            box.add(fruit);
        } else {
            System.out.println(fruit.getName() + " не может лежать в этой коробке");
        }
    }

    public void addAllFruit(T... fruits) {
        if (box.isEmpty()) {
            obj = fruits[0];
        }
        for (T fruit : fruits) {
            if (fruit.getClass() == obj.getClass()) {
                addFruit(fruit);
            } else {
                System.out.println(fruit.getName() + " не может лежать в этой коробке");
            }
        }
    }

    public void deleteAllInBox() {
        box.clear();
        obj = null;
        weight = 0;
    }

    public void printFruits() {
        System.out.println(box);
    }

    public boolean compere(Box<?> box) {
        if (this.getWeightAll() - box.getWeightAll() < 0.0001) {
            return true;
        } else {
            return false;
        }
    }

    public void pour(Box<T> toBox) {
        if (toBox.getWeightAll() < 0.0001) {
            for (T fruits : box) {
                toBox.addFruit(fruits);
            }
            deleteAllInBox();
        } else if (toBox.getWeightAll() > 0.5) {
            if (obj.getClass() == toBox.getObj().getClass()) {
                for (T fruits : box) {
                    toBox.addFruit(fruits);
                }
                deleteAllInBox();
            } else {
                System.out.println("Фрукты в корзине не совместимы");
            }

        }
    }

    private T getObj() {
        return obj;
    }
}
