package com.kotall.learn.proxy.lesson5;

import org.junit.Test;

import com.kotall.learn.proxy.service.OrderService;
import com.kotall.learn.proxy.service.OrderServiceImpl;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class Client5 {

    @Test
    public void testProxy() throws Exception {

        OrderServiceImpl target = new OrderServiceImpl();
        TimeInvocationHandler invocationHandler = new TimeInvocationHandler(target);
        OrderService timeOrderProxy = (OrderService) Proxy.newProxyInstance(OrderService.class, invocationHandler);
        timeOrderProxy.order();
    }

}
