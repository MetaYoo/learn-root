package com.kotall.learn.springext;

/**
 * desc:
 *
 * @author zpwang
 * @date 2020/3/24 16:10
 * @since 1.0.0
 */
public class PlainBean {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String say(String content) {
        return "hi " + content;
    }
}
