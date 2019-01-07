package com.kotall.learn;

import com.kotall.learn.spring.service.BlogService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlogService blogService = (BlogService) ctx.getBean("blogService");
        blogService.post("20190107");
    }
}
