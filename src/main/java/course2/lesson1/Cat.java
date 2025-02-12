package course2.lesson1;

public class Cat implements Movable {
    private String name;
    private int maxRun;
    private int maxJump;

    public Cat(String name, int maxRun, int maxJump) {
        this.name = name;
        this.maxRun = maxRun;
        this.maxJump = maxJump;
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
