package com.kotall.learn.proxy.lesson3;

import com.kotall.learn.proxy.order.OrderService;
import org.junit.Test;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class Client3 {

    @Test
    public void testProxy() throws Exception {
        TimeProxy.newProxyInstance(OrderService.class);
    }
}
