package com.kotall.learn.rpc;

import java.io.Serializable;

/**
 * desc:
 *
 * @author zpwang
 * @date 2020/3/3 15:53
 * @since 1.0.0
 */
public class User implements Serializable {
    private Integer id;
    private String name;

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
