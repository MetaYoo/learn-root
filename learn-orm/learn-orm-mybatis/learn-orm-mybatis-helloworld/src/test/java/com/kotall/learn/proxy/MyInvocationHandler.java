package com.kotall.learn.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class MyInvocationHandler implements InvocationHandler {

    private Object target;
    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(proxy.getClass().getName());
        return method.invoke(target, args);
    }
}
