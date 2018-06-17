package com.kotall.learn.proxy;

import com.kotall.learn.proxy.service.OrderService;
import com.kotall.learn.proxy.service.OrderServiceImpl;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class Client {

    public static void main(String[] args) throws Exception {
//        UserService userService = new LogProxy();
//        userService.addUser();

//        UserService target = new UserServiceImpl();
//
//        UserService timeProxy = new TimeProxy(target);
//
//        UserService logProxy = new LogProxy(timeProxy);
//
//        logProxy.addUser();


        // LogProxy
//        UserService proxy = (UserService)Proxy.newProxyInstance(UserService.class);
//
//        proxy.addUser();


        OrderService target = new OrderServiceImpl();

        OrderService logProxy = (OrderService)Proxy.newProxyInstance(OrderService.class, new LogInvocationHandler(target));
//        logProxy.order();
//
//        logProxy.refund();

        OrderService txProxy = (OrderService)Proxy.newProxyInstance(OrderService.class, new TxInvocationHandler(logProxy));


        OrderService timeProxy = (OrderService)Proxy.newProxyInstance(OrderService.class, new TimeInvocationHandler(txProxy));

        System.out.println(timeProxy.getClass().getName());

        timeProxy.order();
//        timeProxy.refund();






    }
}
