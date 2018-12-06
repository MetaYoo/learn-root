package com.kotall.learn.demo.login.entity;

import lombok.Data;

/**
 * @author zpwang
 * @version 1.0.0
 */
@Data
public class UserEntity {
    private Long id;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 权限
     */
    private String roles;
}
