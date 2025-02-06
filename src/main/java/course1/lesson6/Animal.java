package course1.lesson6;

public abstract class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void run(int distance) {
        System.out.println(name + "  пробежал " + distance + " м.");
    }

    public void swim(int distance) {
        System.out.println(name + "  проплыл " + distance + " м.");
    }
}
