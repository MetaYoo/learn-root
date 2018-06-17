package com.kotall.learn.proxy;

import java.lang.reflect.*;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class JdkTimeInvocationHandler implements java.lang.reflect.InvocationHandler {

    private Object target;
    public JdkTimeInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("======时间统计开始");
        Object ret = method.invoke(target, args);
        System.out.println("======时间统计结束");
        return ret;
    }
}
