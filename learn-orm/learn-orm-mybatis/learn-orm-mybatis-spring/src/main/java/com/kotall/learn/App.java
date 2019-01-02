package com.kotall.learn;

import com.kotall.learn.mybatis.Blog;
import com.kotall.learn.service.BlogService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlogService blogService = (BlogService) ctx.getBean("blogService");
        Blog blog = blogService.getById(1);
        System.out.println(blog);
    }

}
