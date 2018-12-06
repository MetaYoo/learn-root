package com.kotall.learn.shiro.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/3/25 0025 上午 7:53
 */
@Data
@Entity
public class AuthUser {

    @Id
    private String id;
    private String name;
    private String password;
    private String email;
    private String salt;

    public AuthUser() {
    }

    public AuthUser(String name, String password, String email, String salt) {
        this.name = name;
        this.password = password;
    }
}
