package course2.lesson1;

public interface Movable {

    default void run(){
        System.out.println("Я пробежал");
    }

    default void jump(){
        System.out.println("Я перепрыгнул");
    }
}
