package com.kotall.learn.proxy.lesson7;

import org.junit.Test;

import com.kotall.learn.proxy.service.AccountService;
import com.kotall.learn.proxy.service.AccountServiceImpl;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class Client7 {

    @Test
    public void testProxy() throws Exception {

        AccountService target = new AccountServiceImpl();
        
        TimeInvocationHandler invocationHandler = new TimeInvocationHandler(target);
        AccountService proxy = (AccountService) Proxy.newProxyInstance(AccountService.class, invocationHandler);
        String ret = proxy.createAccount("arac", "123456", 100);
    }

}
