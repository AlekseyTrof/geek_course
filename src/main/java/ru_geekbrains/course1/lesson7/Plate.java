package ru_geekbrains.course1.lesson7;

public class Plate {
    private int food;

    public Plate(int food) {
        this.food = food;
    }

    public void decreaseFood(Cat cat ,int n) {
        if (food > n) {
            food -= n;
            cat.setWellFed(true);
        } else {
            cat.setWellFed(false);
        }
    }

    public void info() {
        System.out.println("plate: " + food);
    }

    public void addFood(int food) {
        this.food += food;
    }
}
