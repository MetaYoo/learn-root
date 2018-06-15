package com.kotall.learn.i18n.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: aracwong
 * @email: aracwong@163.com
 * @datetime: 2017/8/12 0012 上午 11:24
 * @version: 1.0.0
 */
public class UserServiceTest {
    private ApplicationContext ctx;
    private UserService userService;

    @Before
    public void setUp() {
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        userService = (UserService)ctx.getBean("userService");
    }

    @Test
    public void testFormatLog1() {
        String rs = userService.formatLog1("username");
        System.out.println(rs);
    }

    @Test
    public void testFormatLog2() {
        String rs = userService.formatLog2("password");
        System.out.println(rs);
    }

    @Test
    public void testFormatLog3() {
        String rs = userService.formatLog3("email");
        System.out.println(rs);
    }
}
