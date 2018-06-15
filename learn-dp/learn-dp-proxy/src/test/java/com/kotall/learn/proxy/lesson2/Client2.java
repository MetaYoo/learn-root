package com.kotall.learn.proxy.lesson2;

import org.junit.Test;

import com.kotall.learn.proxy.service.OrderService;
import com.kotall.learn.proxy.service.OrderServiceImpl;

public class Client2 {


	@Test
	public void testInterface2() {
		OrderService target = new OrderServiceImpl();
		OrderService proxy = new TimeOrderProxy(target);
		proxy.order();
	}
	
	@Test
	public void testNestProxy2() {
		OrderService target = new OrderServiceImpl();
		
		OrderService timeProxy = new TimeOrderProxy(target);
		OrderService logProxy = new LogOrderProxy(timeProxy);
		logProxy.order();
		
		
	}
}
