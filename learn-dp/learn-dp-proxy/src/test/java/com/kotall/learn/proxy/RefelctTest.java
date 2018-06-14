package com.kotall.learn.proxy;

import com.kotall.learn.proxy.order.OrderService;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class RefelctTest {

    @Test
    public void testReflect() throws Exception {
        Method[] methods = OrderService.class.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }

        Method method = OrderService.class.getMethod("order");
        System.out.println(method.getName());
    }

}
