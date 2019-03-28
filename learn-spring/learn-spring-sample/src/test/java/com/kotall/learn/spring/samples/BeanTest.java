package com.kotall.learn.spring.samples;

import com.kotall.learn.spring.samples.service.WeatherService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class BeanTest {

    @Test
    public void beanSample() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext1.xml");
        WeatherService weatherService = ctx.getBean("weatherService", WeatherService.class);
        weatherService.report("SH");
    }
}
