package com.kotall.learn.proxy.lesson4;

import org.junit.Test;

import com.kotall.learn.proxy.service.OrderService;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class Client4 {

    @Test
    public void testProxy() throws Exception {
        OrderService timeOrderProxy = (OrderService) Proxy.newProxyInstance(OrderService.class);
        timeOrderProxy.order();
    }

}
