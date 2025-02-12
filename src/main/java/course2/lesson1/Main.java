package course2.lesson1;


public class Main {

    public static void main(String[] args) {
        Movable[] members = {new Cat("Маркиз", 50, 3),
                new Person("Алексей", 100, 2),
                new Robot("Марионетка", 40,1)};

        Object[] obstacles = {new Treadmill(50), new Wall(2)};

        boolean[] siccesses = new boolean[members.length];

        for (int i = 0; i < members.length; i++) {
            if (obstacles[0] instanceof Treadmill) {
                siccesses[i] =((Treadmill) obstacles[0]).siccess(members[i]);
            }
            if (siccesses[i]) {
                if (obstacles[1] instanceof Wall) {
                    ((Wall) obstacles[1]).siccess(members[i]);
                }
            }
            System.out.println();
        }

    }
}

