package com.kotall.learn;

import com.kotall.learn.spring.BlogService;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-local.xml");
        System.out.println("======================开始初始化Spring容器========================");

        BlogService blogService = (BlogService)ctx.getBean("blogService");
        blogService.post("你好啊！！");

        TimeUnit.SECONDS.sleep(5);

        //FileSystemResource resource = new FileSystemResource("applicationContext-remote.xml");
        ClassPathResource resource = new ClassPathResource("applicationContext-remote.xml");
        ConfigurableApplicationContext nCtx = (ConfigurableApplicationContext)ctx;
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) nCtx.getBeanFactory();


        new XmlBeanDefinitionReader(beanFactory).loadBeanDefinitions(resource);

//        String[] names = beanFactory.getBeanDefinitionNames();
//        for (String name : names) {
//            beanFactory.getBean(name);
//        }

        blogService = (BlogService) beanFactory.getBean("blogService");
        blogService.post("你好啊！！");



    }
}
