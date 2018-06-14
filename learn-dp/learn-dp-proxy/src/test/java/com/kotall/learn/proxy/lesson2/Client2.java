package com.kotall.learn.proxy.lesson2;

import com.kotall.learn.proxy.order.OrderService;
import com.kotall.learn.proxy.order.OrderServiceImpl;
import org.junit.Test;

public class Client2 {


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
