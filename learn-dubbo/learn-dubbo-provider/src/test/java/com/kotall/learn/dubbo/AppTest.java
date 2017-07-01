package com.kotall.learn.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: aracwong
 * @email: aracwong@163.com
 * @datetime: 2017/7/1 0001 下午 3:20
 * @version: 1.0.0
 */
public class AppTest {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring-dubbo-provider.xml");
        ctx.start();
        String[] names = ctx.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
        Thread.sleep(10 * 60 * 60 * 1000);
    }
}
