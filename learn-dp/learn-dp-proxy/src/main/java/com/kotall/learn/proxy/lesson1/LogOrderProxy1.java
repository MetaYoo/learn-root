package com.kotall.learn.proxy.lesson1;

import com.kotall.learn.proxy.order.OrderServiceImpl;

public class LogOrderProxy1 extends OrderServiceImpl {

	@Override
	public void order() {
		System.out.println("===下单处理开始");
		super.order();
		System.out.println("===下单处理结束");
	}

	
}
