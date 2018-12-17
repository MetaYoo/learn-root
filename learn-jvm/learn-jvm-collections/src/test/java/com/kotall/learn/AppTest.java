package com.kotall.learn;

import com.kotall.learn.jvm.Animal;
import com.kotall.learn.jvm.Cat;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void shouldAnswerWithTrue() {
        Animal animal = new Cat();
        int age = new App().test(animal);
        System.out.println(age);
    }
}
