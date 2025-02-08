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
    public void run(int distance) {
        if (distance <= getMaxRun()) {
            System.out.println(getName() + " пробежал " + distance + " м.");
        }
    }

    @Override
    public void jump(int height) {
        if (height <= getMaxJump()) {
            System.out.println(getName() + " перепрыгнул " + height + " м.");
        }
    }
}
