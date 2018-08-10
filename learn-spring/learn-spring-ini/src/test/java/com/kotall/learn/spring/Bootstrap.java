package com.kotall.learn.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: aracwong
 * @email: aracwong@163.com
 * @datetime: 2017/9/25 0025 下午 2:01
 * @version: 1.0.0
 */
public class Bootstrap {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-context.xml");
        ctx.start();
        System.out.println("======================================================");
        AnimalService paymentService = (AnimalService)ctx.getBean("paymentService");
        paymentService.run();
    }
}
