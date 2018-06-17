package com.kotall.learn.proxy;

import com.kotall.learn.proxy.service.OrderService;
import com.kotall.learn.proxy.service.OrderServiceImpl;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class JdkProxy {

    public static void main(String[] args) {
        OrderService target = new OrderServiceImpl();

        OrderService timeProxy = (OrderService)java.lang.reflect.Proxy.newProxyInstance(JdkProxy.class.getClassLoader(), new Class<?>[]{OrderService.class}, new JdkTimeInvocationHandler(target));

        System.out.println(timeProxy.getClass().getName());
        timeProxy.order();

    }
}
