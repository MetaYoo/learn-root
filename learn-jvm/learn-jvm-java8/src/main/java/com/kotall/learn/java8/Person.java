package com.kotall.learn.java8;

import java.util.List;

/**
 * desc:
 *
 * @author zpwang
 * @date 2019/5/18 10:30
 * @since 1.0.0
 */
public class Person {
    private String name;
    List<String> childList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getChildList() {
        return childList;
    }

    public void setChildList(List<String> childList) {
        this.childList = childList;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", childList=" + childList +
                '}';
    }
}
