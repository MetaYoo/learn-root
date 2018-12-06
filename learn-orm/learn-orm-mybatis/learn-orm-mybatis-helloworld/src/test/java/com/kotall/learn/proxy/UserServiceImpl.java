package com.kotall.learn.proxy;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class UserServiceImpl implements UserService {
    @Override
    public void saveUser() {
        System.out.println("===== add user =====");
    }
}
