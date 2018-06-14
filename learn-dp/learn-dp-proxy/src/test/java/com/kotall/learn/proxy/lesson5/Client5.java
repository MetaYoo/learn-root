package com.kotall.learn.proxy.lesson5;

import com.kotall.learn.proxy.order.OrderService;
import com.kotall.learn.proxy.order.OrderServiceImpl;
import org.junit.Test;

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
