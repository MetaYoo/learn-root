package com.kotall.learn.proxy.lesson7;

import java.lang.reflect.Method;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class TimeInvocationHandler implements InvocationHandler {

    private Object target;

    TimeInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
        System.out.println("== startTime");
        long startTime = System.currentTimeMillis();
        Object ret = method.invoke(target, args);
        long endTime = System.currentTimeMillis();
        System.out.println("== endTime, cost: " + (endTime - startTime));
        return ret;
    }
}
