package course1.lesson7;

public class MainClass {

    public static void main(String[] args) {
        Cat[] cats = new Cat[]{new Cat("Barsik", 5),
                new Cat("Bar", 7),
                new Cat("Var", 10),
                new Cat("Nal", 15),
                new Cat("Mal", 15)};
        Plate plate = new Plate(50);

        for (Cat cat : cats) {
            cat.eat(plate);
        }
        for (Cat cat : cats) {
            System.out.println(cat.getName() + " наелся? " + cat.isWellFed());
        }
        plate.info();


    }
}
