package com.kotall.learn.proxy.service;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class OrderServiceImpl implements OrderService {
    @Override
    public void order() {
        System.out.println("====== 下单");
    }

    @Override
    public void refund() {
        System.out.println("======  对单");
    }
}
