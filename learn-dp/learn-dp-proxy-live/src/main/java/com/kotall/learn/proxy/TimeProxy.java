package com.kotall.learn.proxy;

import com.kotall.learn.proxy.service.UserService;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class TimeProxy implements UserService {

    private UserService target;

    public TimeProxy(UserService target) {
        this.target = target;
    }

    @Override
    public void addUser() {
        long startTime = System.currentTimeMillis();

        System.out.println("==时间统计开始");
        this.target.addUser();

        long endTime = System.currentTimeMillis();

        System.out.println("==时间统计结束： " + (endTime - startTime));
    }

    @Override
    public void deleteUser() {

    }
}
