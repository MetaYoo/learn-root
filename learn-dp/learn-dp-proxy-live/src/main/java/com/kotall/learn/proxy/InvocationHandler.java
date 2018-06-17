package com.kotall.learn.proxy;

import java.lang.reflect.Method;

/**
 * @author zpwang
 * @version 1.0.0
 */
public interface InvocationHandler {

    Object invoke(Object proxy, Method method);
}
