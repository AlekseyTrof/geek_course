package ru_geekbrains.course1.lesson5;

public class Worker {
    private String nameFull;
    private String position;
    private String email;
    private String phoneNumber;
    private int salary;
    private int age;

    public Worker(String nameFull, String position, String email, String phoneNumber, int salary, int age) {
        this.nameFull = nameFull;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.age = age;
    }

    public void info() {
        System.out.println("Name - " + nameFull + "\nPosition - " + position + "\nEmail - " + email +
                "\nPhone number - " + phoneNumber + "\nSalary - " + salary + "\nAge - " + age);
    }

    public String getNameFull() {
        return nameFull;
    }

    public String getPosition() {
        return position;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }
}
