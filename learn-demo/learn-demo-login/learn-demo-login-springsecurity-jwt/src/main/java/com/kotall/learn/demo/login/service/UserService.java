package com.kotall.learn.demo.login.service;

import com.kotall.learn.demo.login.entity.UserEntity;

/**
 * @author zpwang
 * @version 1.0.0
 */
public interface UserService {

    boolean insert(UserEntity userEntity);

    UserEntity getByUsername(String username);
}
