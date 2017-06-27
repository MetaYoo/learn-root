package com.kotall.learn.logback;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author aracwong
 * @version 1.0.0
 * @email
 * @datetime 2017/6/26 0026 下午 4:50
 */
public class App {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring-context.xml");
        ctx.start();
        String[] names = ctx.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
        DemoService demoService = (DemoService) ctx.getBean("demoService");
        demoService.demo();

        Thread.sleep(100000 * 60 * 60 * 1000L);
    }
}
