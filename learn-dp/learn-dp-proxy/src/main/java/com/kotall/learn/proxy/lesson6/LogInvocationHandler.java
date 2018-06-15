package com.kotall.learn.proxy.lesson6;

import java.lang.reflect.Method;

public class LogInvocationHandler  implements InvocationHandler {

	private Object target;
	
	public LogInvocationHandler(Object target) {
		this.target = target;
	}
	
	@Override
	public Object invoke(Object proxy, Method method) throws Exception {
		System.out.println("=== 下单处理开始");
		Object ret = method.invoke(target, null);
		System.out.println("=== 下单处理结束");
		return ret;
	}

}
