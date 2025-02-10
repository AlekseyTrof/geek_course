package cource2.lesson1;

public class Treadmill {
    private int length;

    public Treadmill(int length) {
        this.length = length;
    }

    public boolean siccess(Movable member) {
        if (member instanceof Cat) {
            Cat cat = (Cat) member;
            if (cat.getMaxRun() >= length) {
                cat.run();
                return true;
            } else {
                System.out.println(cat.getName() + " не смог пробежать и выбывает из игры");
            }
        } else if (member instanceof Person) {
            Person person = (Person) member;
            if (person.getMaxRun() >= length) {
                person.run();
                return true;
            } else {
                System.out.println(person.getName() + " не смог пробежать и выбывает из игры");
            }
        } else if (member instanceof Robot) {
            Robot robot = (Robot) member;
            if (robot.getMaxRun() >= length) {
                robot.run();
                return true;
            } else {
                System.out.println(robot.getName() + " не смог пробежать и выбывает из игры");
            }
        }
        return false;
    }

    public int getLength() {
        return length;
    }
}
