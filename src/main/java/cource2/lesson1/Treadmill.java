package cource2.lesson1;

public class Treadmill {
    private int length;

    public Treadmill(int length) {
        this.length = length;
    }

    public boolean siccess(Movable move, int length) {
        if (length >= this.length) {
            move.run(length);
            return true;
        } else {
            move.run(length);
            return false;
        }
    }

    public int getLength() {
        return length;
    }
}
