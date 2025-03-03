package course3.lesson1.taskThree;

public class Apple extends Fruit {
    private static String name = "Яблоко";
    private float weight = 1f;


    @Override
    public float getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
