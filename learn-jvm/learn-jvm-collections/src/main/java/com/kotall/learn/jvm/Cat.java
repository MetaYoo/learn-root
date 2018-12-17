package com.kotall.learn.jvm;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class Cat extends Animal {

    static {
        System.out.println("====== child static ======");
    }

    public Cat() {
        System.out.println("====== child constructor ======");
    }

    public int getAge() {
        return 100;
    }

    public static void main(String[] args) {
        Animal animal = new Cat();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        animal = new Cat();

        System.out.println(animal.getAge());
    }
}
