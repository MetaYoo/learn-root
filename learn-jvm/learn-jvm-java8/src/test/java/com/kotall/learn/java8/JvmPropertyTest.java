package com.kotall.learn.java8;

import java.util.Properties;

public class JvmPropertyTest {
    public static void main(String[] args) {
        Properties properties = System.getProperties();
        properties.forEach((k, v) -> {
            System.out.println(k + "=" + v);
        });

        System.out.println("======================");
        System.out.println(properties.get("sun.boot.class.path"));
        System.out.println("=========================");
        System.out.println(properties.get("java.ext.dirs"));
    }
}
