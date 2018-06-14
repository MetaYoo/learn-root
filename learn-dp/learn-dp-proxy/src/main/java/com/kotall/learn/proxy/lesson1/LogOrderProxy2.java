package com.kotall.learn.proxy.lesson1;

import com.kotall.learn.proxy.order.OrderService;

public class LogOrderProxy2 implements OrderService {
	private OrderService target;
	
	public LogOrderProxy2(OrderService target) {
		this.target = target;
	}
	
	@Override
	public void order() {
		System.out.println("=== 下单处理开始");
		target.order();
		System.out.println("=== 下单处理结束");
	}

	
}
