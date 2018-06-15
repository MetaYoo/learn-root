package com.kotall.learn.proxy;

import org.junit.Test;

import com.kotall.learn.proxy.service.OrderServiceImpl;

import net.sf.cglib.proxy.Enhancer;

public class CGLibTest {

	@Test
	public void testCGLib() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(OrderServiceImpl.class);
//		enhancer.setCallback(callback);
		enhancer.create();
	}

}
