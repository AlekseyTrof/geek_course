package cource1.lesson7;

public class Cat {
    private String name;
    private int appetite;
    private boolean wellFed;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        wellFed = false;
    }

    public void eat(Plate p) {
        p.decreaseFood(this,appetite);
    }

    public boolean isWellFed() {
        return wellFed;
    }

    public void setWellFed(boolean wellFed) {
        this.wellFed = wellFed;
    }

    public String getName() {
        return name;
    }
}
