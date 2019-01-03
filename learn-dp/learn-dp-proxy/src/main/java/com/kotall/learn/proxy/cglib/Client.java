package com.kotall.learn.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class Client {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Man.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("===== before =====");
                methodProxy.invokeSuper(o, objects);
                System.out.println("===== after =====");
                return null;
            }
        });

        Human human = (Human) enhancer.create();

        human.show();
    }
}
