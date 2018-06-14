package com.kotall.learn.proxy.lesson1;

import org.junit.Test;

import com.kotall.learn.proxy.order.OrderService;

public class Client {

	@Test
	public void test() {
		OrderService orderService = new TimeOrderProxy();
		orderService.order();
	}
}
