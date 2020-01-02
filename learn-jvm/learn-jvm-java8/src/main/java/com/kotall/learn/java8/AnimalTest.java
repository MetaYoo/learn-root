package com.kotall.learn.java8;

/**
 * desc:
 *
 * @author zpwang
 * @date 2020/1/2 12:23
 * @since 1.0.0
 */
public class AnimalTest {
    public static void main(String[] args) {
        Animal animal = create();
        animal.eat("123");
    }

    private static Animal create() {
        return (animal) -> System.out.println(animal);
    }
}


