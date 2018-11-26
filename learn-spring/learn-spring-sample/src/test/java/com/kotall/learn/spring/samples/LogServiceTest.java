package com.kotall.learn.spring.samples;

import com.kotall.learn.spring.samples.service.LogService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Unit test for simple App.
 */
public class LogServiceTest
{
    @Test
    public void sampleApp()
    {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext1.xml");
        LogService logService = (LogService) ctx.getBean("logService");
        logService.addLog("hello");

    }
}
