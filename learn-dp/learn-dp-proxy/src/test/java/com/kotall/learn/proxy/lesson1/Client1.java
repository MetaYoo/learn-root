package com.kotall.learn.proxy.lesson1;

import com.kotall.learn.proxy.lesson2.LogOrderProxy2;
import com.kotall.learn.proxy.lesson2.TimeOrderProxy2;
import com.kotall.learn.proxy.service.OrderService;
import com.kotall.learn.proxy.service.OrderServiceImpl;

import org.junit.Test;

public class Client1 {

	@Test
	public void testExtend1() {
		OrderService orderService = new TimeOrderProxy1();
		orderService.order();
	}
	
	@Test
	public void testInterface2() {
		OrderService target = new OrderServiceImpl();
		OrderService proxy = new TimeOrderProxy2(target);
		proxy.order();
	}
	
	@Test
	public void testNestProxy2() {
		OrderService target = new OrderServiceImpl();
		
		OrderService timeProxy = new TimeOrderProxy2(target);
		OrderService logProxy = new LogOrderProxy2(timeProxy);
		logProxy.order();
		
		
	}
}
