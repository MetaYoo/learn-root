package com.kotall.learn.proxy;

import java.lang.reflect.Method;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class TimeInvocationHandler implements InvocationHandler {

    private Object target;

    public TimeInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method) {
        System.out.println(proxy.getClass().getName());
        //System.out.println("时间统计开始");
        // 安全检查
        Object ret = null;
        try {
            // 核心的业务处理
            ret =  method.invoke(target);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println("时间统计结束");
        return ret;
    }
}
