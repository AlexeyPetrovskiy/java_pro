package ru.innotech.education;

public class Employee {

    enum Position{
        ENGINEER, DIRECTOR, MANAGER
    }

    String name;
    Integer age;
    Position position;

    public Employee(String name, Integer age, Position position) {
        this.name = name;
        this.age = age;
        this.position = position;
    }

    @Override
    public String toString() {
        return name + " " + age;
    }

    public Integer getAge() {
        return age;
    }
}
