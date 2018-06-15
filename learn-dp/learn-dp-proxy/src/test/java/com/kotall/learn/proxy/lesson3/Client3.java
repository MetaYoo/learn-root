package com.kotall.learn.proxy.lesson3;

import org.junit.Test;

import com.kotall.learn.proxy.service.OrderService;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class Client3 {

    @Test
    public void testTimeOrderProxy() throws Exception {
        OrderService timeOrderProxy = (OrderService)Proxy.newProxyInstance(OrderService.class);
        timeOrderProxy.order();
    }
}
