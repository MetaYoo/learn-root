package com.kotall.learn.proxy;

import java.lang.reflect.Proxy;

import org.junit.Test;

import com.kotall.learn.proxy.service.OrderService;
import com.kotall.learn.proxy.service.OrderServiceImpl;
public class JdkProxyTest {

	@Test
	public void testJdkProxy() {
		OrderService timeProxy = (OrderService)Proxy.newProxyInstance(JdkProxyTest.class.getClassLoader(), new Class[]{OrderService.class}, new JdkProxyTimeHandler(new OrderServiceImpl()));
		timeProxy.order();
	}
	
}
