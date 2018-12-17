package com.kotall.learn.jvm;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class Animal {

    static {
        System.out.println("------ parent static -------");
    }

    public Animal() {
        System.out.println("------ parent constructor --------");
    }
    private String name;

    public String getName() {
        return name;
    }

    public int getAge() {
        return 10;
    }
}
