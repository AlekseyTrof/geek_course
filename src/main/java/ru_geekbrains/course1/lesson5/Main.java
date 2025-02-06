package ru_geekbrains.course1.lesson5;

public class Main {

    public static void main(String[] args) {
        Worker[] workers = new Worker[]{
            new Worker("Alex Trofimov", "QA", "alex.99@mail.ru", "321544", 23000, 34),
            new Worker("Oleg Ivanov", "QA", "oleg.95@mail.ru", "321543", 24000, 41),
            new Worker("Igor Davidov", "Dev", "igor.80@mail.ru", "324444", 25000, 55),
            new Worker("Alena Makarova", "PM", "alena.79@mail.ru", "321664", 26000, 33),
            new Worker("Vladislav Zorro", "Dev", "vlad.99@mail.ru", "321224", 28000, 24)
        };

        for (int i = 0; i < workers.length; i++) {
            if (workers[i].getAge() > 40) {
                workers[i].info();
                System.out.println();
            }
        }

    }

}
