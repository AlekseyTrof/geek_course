package cource2.lesson1;

public interface Movable {

    default void run(int distance){
        System.out.println("Я пробежал" + distance + " м.");
    }

    default void jump(int height){
        System.out.println("Я перепрыгнул" + height + " м.");
    }
}
