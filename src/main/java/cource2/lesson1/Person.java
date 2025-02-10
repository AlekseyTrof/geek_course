package cource2.lesson1;

public class Person implements Movable {
    private String name;
    private int maxRun;
    private int maxJump;

    public Person(String name, int maxRun, int maxJump) {
        this.name = name;
        this.maxRun = maxRun;
        this.maxJump = maxJump;
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getMaxRun() {
        return maxRun;
    }

    public int getMaxJump() {
        return maxJump;
    }

    @Override
    public void run() {
        System.out.println(getName() + " пробежал это расстояние и продолжает игру" );
    }

    @Override
    public void jump() {
        System.out.println(getName() + " перепрыгнул эту высоту и продолжает игру");
    }
}
