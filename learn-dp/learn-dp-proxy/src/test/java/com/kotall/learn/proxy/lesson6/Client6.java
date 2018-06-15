package com.kotall.learn.proxy.lesson6;

import com.kotall.learn.proxy.order.OrderService;
import com.kotall.learn.proxy.order.OrderServiceImpl;
import org.junit.Test;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class Client6 {

    @Test
    public void testProxy() throws Exception {

        OrderServiceImpl target = new OrderServiceImpl();
        
        TimeInvocationHandler invocationHandler = new TimeInvocationHandler(target);
        OrderService timeOrderProxy = (OrderService) Proxy.newProxyInstance(OrderService.class, invocationHandler);
        timeOrderProxy.order();
    }
    
    
    @Test
    public void testProxy2() throws Exception {

        OrderServiceImpl target = new OrderServiceImpl();
        
        TimeInvocationHandler timeHandler = new TimeInvocationHandler(target);
        OrderService timeOrderProxy = (OrderService) Proxy.newProxyInstance(OrderService.class, timeHandler);
        
        LogInvocationHandler logHandler = new LogInvocationHandler(timeOrderProxy);
        
        OrderService logOrderProxy = (OrderService) Proxy.newProxyInstance(OrderService.class, logHandler);
        
        logOrderProxy.order();
    }

}
