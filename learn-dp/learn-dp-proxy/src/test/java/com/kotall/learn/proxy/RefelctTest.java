package com.kotall.learn.proxy;

import org.junit.Test;

import com.kotall.learn.proxy.service.OrderService;

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
