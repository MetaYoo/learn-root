package com.kotall.learn.proxy.lesson1;

import com.kotall.learn.proxy.lesson2.LogOrderProxy;
import com.kotall.learn.proxy.lesson2.TimeOrderProxy;
import com.kotall.learn.proxy.service.OrderService;
import com.kotall.learn.proxy.service.OrderServiceImpl;

import org.junit.Test;

public class Client1 {
	
	@Test
	public void testInterface1() {
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
