package com.kotall.learn.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JdkProxyTimeHandler implements InvocationHandler {

	private Object target;
	
	JdkProxyTimeHandler(Object target) {
		this.target = target;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println(proxy.getClass().getName());
		System.out.println("-----------------------------");
		System.out.println("== startTime");
        long startTime = System.currentTimeMillis();
        Object ret = method.invoke(target);
        long endTime = System.currentTimeMillis();
        System.out.println("== endTime, cost: " + (endTime - startTime));

		return ret;
	}

}
