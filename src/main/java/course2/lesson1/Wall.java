package course2.lesson1;

public class Wall {
    private int height;

    public Wall(int height) {
        this.height = height;
    }

    public boolean siccess(Movable member) {
        if (member instanceof Cat) {
            Cat cat = (Cat) member;
            if (cat.getMaxJump() >= height) {
                cat.jump();
                return true;
            } else {
                System.out.println(cat.getName() + " не смог перепрыгнуть и выбывает из игры");
            }
        } else if (member instanceof Person) {
            Person person = (Person) member;
            if (person.getMaxJump() >= height) {
                person.jump();
                return true;
            } else {
                System.out.println(person.getName() + " не смог перепрыгнуть и выбывает из игры");
            }
        } else if (member instanceof Robot) {
            Robot robot = (Robot) member;
            if (robot.getMaxJump() >= height) {
                robot.jump();
                return true;
            }
            else {
                System.out.println(robot.getName() + " не смог перепрыгнуть и выбывает из игры");
            }
        }
        return false;
    }

    public int getHeight() {
        return height;
    }
}
