package course3.lesson1.taskThree;

public class Orange extends Fruit {
    private static String name = "Апельсин";
    private float weight = 1.5f;

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
