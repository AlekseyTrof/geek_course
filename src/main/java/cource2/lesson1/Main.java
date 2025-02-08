package cource2.lesson1;

public class Main {

    public static void main(String[] args) {
        Movable[] animal = {new Cat("Маркиз", 50, 3),
                new Person("Алексей", 100, 2),
                new Robot("Марионетка", 50,1)};

        Object[] obstacles = {new Treadmill(100), new Wall(2)};

        if (obstacles[0] instanceof Treadmill) {
            Treadmill obst1 = ((Treadmill) obstacles[0]);
            for (int i = 0; i < animal.length; i++) {
                boolean result = obst1.siccess(animal[i], obst1.getLength());
                if (result) {
                    if (obstacles[1] instanceof Wall) {
                        Wall wall = ((Wall) obstacles[1]);
                            wall.siccess(animal[i], wall.getHeight());
                        }
                    }
                System.out.println();
                }
            }

        }
    }
