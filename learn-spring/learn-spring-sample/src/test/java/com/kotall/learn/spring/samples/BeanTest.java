package com.kotall.learn.spring.samples;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class BeanTest {

    @Test
    public void beanSample()
    {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext2.xml");

    }
}
