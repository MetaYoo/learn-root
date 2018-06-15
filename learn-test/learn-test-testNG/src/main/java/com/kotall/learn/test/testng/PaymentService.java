package com.kotall.learn.test.testng;

/**
 * @author : zpwang
 * @version : 1.0.0
 * @date : 2018/3/16
 */
public interface PaymentService {

    String pay(String orderId);

    int count(String orderId);
}
