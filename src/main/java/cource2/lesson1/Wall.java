package cource2.lesson1;

public class Wall {
    private int height;

    public Wall(int height) {
        this.height = height;
    }

    public boolean siccess(Movable move, int height) {
        if (height >= this.height) {
            move.jump(height);
            return true;
        } else {
            return false;
        }
    }

    public int getHeight() {
        return height;
    }
}
