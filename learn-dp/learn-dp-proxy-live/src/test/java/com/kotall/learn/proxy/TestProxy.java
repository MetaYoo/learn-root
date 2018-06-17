package com.kotall.learn.proxy;

import com.kotall.learn.proxy.service.OrderService;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class TestProxy {

    @Test
    public void testProxy() {
        System.out.println("================");
    }

    @Test
    public void testProxy2() throws Exception {
        Method method = OrderService.class.getMethod("order");
        System.out.println(method.getName());
    }
}
