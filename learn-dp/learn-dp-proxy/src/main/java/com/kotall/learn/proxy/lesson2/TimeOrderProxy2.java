package com.kotall.learn.proxy.lesson2;

import com.kotall.learn.proxy.order.OrderService;

public class TimeOrderProxy2 implements OrderService {

	private OrderService target;
	
	public TimeOrderProxy2(OrderService target) {
		this.target = target;
	}
	
	@Override
	public void order() {
		System.out.println("== startTime");
		long startTime = System.currentTimeMillis();
		target.order();
		long endTime = System.currentTimeMillis();
		System.out.println("== endTime, cost: " + (endTime - startTime));
	}


}
