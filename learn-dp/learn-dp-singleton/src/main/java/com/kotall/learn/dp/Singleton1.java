package com.kotall.learn.dp;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class Singleton1 {

    private Singleton1() {}

    public static Singleton1 getInstance() {
        // 方法中的逻辑只有在用户调用的时候才执行
        // 方法中实现需要分配内存，也是调用时才分配的
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final Singleton1 INSTANCE = new Singleton1();
    }
}
