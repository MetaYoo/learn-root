package com.kotall.learn.proxy;

import java.lang.reflect.Method;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class LogInvocationHandler implements InvocationHandler {

    private Object target;

    public LogInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method) {
        System.out.println("日志统计开始");
        Object ret = null;
        try {
            // 核心的业务处理
           ret =  method.invoke(target);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("日志统计结束");
        return ret;
    }
}
