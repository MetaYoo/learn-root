package com.kotall.learn.shiro.bean;

import lombok.Data;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/3/25 0025 上午 7:53
 */
@Data
public class User {

    private String username;
    private String password;
    private String email;
    private String salt;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
