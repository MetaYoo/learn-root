package com.kotall.learn.proxy.lesson6;

import org.junit.Test;

import com.kotall.learn.proxy.service.OrderService;
import com.kotall.learn.proxy.service.OrderServiceImpl;
import com.kotall.learn.proxy.service.UserService;
import com.kotall.learn.proxy.service.UserServiceImpl;

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
    
    @Test
    public void testProxy3() throws Exception {

    	UserServiceImpl target = new UserServiceImpl();
        
        TimeInvocationHandler timeHandler = new TimeInvocationHandler(target);
        UserService userTimeProxy = (UserService) Proxy.newProxyInstance(UserService.class, timeHandler);
        
        userTimeProxy.addUser();
    }

}
