package com.kotall.learn.objectcopy;

import java.io.Serializable;

public class Person implements Serializable  {
    // 两个属性值：分别代表值传递和引用传递
    private Age age;
    private String name;

    public Person(Age age, String name) {
        this.age = age;
        this.name = name;
    }

    // 拷贝构造方法
    public Person(Person p) {
        this.name = p.name;
        this.age = p.age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name + " " + this.age;
    }
}

class Age implements Serializable {

    private int age;

    public Age(int age) {
        this.age = age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

    public String toString() {
        return getAge() + "";
    }
}