package com.kotall.learn.proxy.lesson5;

import com.kotall.learn.proxy.order.OrderService;
import org.junit.Test;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class Client5 {

    @Test
    public void testProxy() throws Exception {
        OrderService timeOrderProxy = (OrderService) Proxy.newProxyInstance(OrderService.class);
        timeOrderProxy.order();
    }

}
