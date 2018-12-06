package com.kotall.learn.dubbo.service;

import com.kotall.learn.dubbo.api.PaymentService;
import com.kotall.learn.dubbo.api.dto.OrderDto;

/**
 * @author: aracwong
 * @email: aracwong@163.com
 * @datetime: 2017/7/1 0001 下午 3:03
 * @version: 1.0.0
 */
public class PaymentServiceImpl implements PaymentService {
    @Override
    public void pay(long trxAmt) {
        System.out.println("========trxAmt: " + trxAmt);
    }

    @Override
    public void pay(OrderDto order) {
        System.out.println("========Order info: " + order.getDetail());
    }
}
