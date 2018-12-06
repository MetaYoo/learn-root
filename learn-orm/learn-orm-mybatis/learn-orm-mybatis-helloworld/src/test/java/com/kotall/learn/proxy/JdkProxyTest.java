package com.kotall.learn.proxy;

import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class JdkProxyTest {

    @Test
    public void proxy() {
        UserService userService = (UserService) Proxy.newProxyInstance(
                JdkProxyTest.class.getClassLoader(),
                new Class[]{UserService.class},
                new MyInvocationHandler(new UserServiceImpl()));

        userService.saveUser();

    }
}
