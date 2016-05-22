package com.epam.task_4.person;

import java.util.Random;

public class Person {

    private static final String[] NAMES = new String[]{"Alex","Sergey","Dima","Chloe","Maria","Evgenya","Ivan","Dasha","Artem","Ilia"};

    private String name;
    private int age;
    private Sex sex;

    public Person() {
        this.name = NAMES[new Random().nextInt(NAMES.length)];
        this.age = new Random().nextInt(100);
        this.sex = Sex.getSex();
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " : " + age + " : " + sex;
    }

    public enum Sex {
        Male,
        Female;

        public static Sex getSex() {
            return values()[(int) (Math.random() * values().length)];
        }
    }
}
