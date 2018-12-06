package com.kotall.learn;

import com.kotall.learn.dubbo.api.PaymentService;
import com.kotall.learn.dubbo.api.dto.BaseDto;
import com.kotall.learn.dubbo.api.dto.OrderDto;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: aracwong
 * @email: aracwong@163.com
 * @datetime: 2017/7/1 0001 下午 3:54
 * @version: 1.0.0
 */
public class ClientTest {

    @Test
    public void testObject() {
        OrderDto sub = new OrderDto();
        BaseDto p = sub;

        Assert.assertTrue(p instanceof OrderDto);

    }
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring-dubbo-consumer.xml");
        ctx.start();
        String[] names = ctx.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }

        PaymentService paymentService = (PaymentService)ctx.getBean("paymentService");
        for (int i = 0; i < 10000000; i++) {
//            paymentService.pay(100);
            OrderDto order = new OrderDto();
            order.setDetail("微信红包");
            order.setAmount(100L);
            paymentService.pay(order);
            Thread.sleep(1000);
        }
        Thread.sleep(10 * 60 * 60 * 1000);
    }
}
