package com.kotall.learn;

import com.kotall.learn.spring.bean.Blog;
import com.kotall.learn.spring.service.BlogService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        BlogService blogService = (BlogService) ctx.getBean("blogService");
        Blog blog = blogService.getById(1);
        System.out.println(blog);
    }
}
