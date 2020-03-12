package com.kotall.learn.java8;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/**
 * desc:
 *
 * @author zpwang
 * @date 2020/3/2 14:29
 * @since 1.0.0
 */
public class JOLTest {
    public static void main(String[] args) {
        Person person = new Person();
        ClassLayout classLayout = ClassLayout.parseInstance(person);
        String s = classLayout.toPrintable();
        System.out.println(s);
        System.out.println("=====================================================");
        System.out.println(VM.current().details());

    }
}
