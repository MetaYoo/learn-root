package com.kotall.learn.springext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * desc:
 *
 * @author zpwang
 * @date 2020/3/24 16:12
 * @since 1.0.0
 */
public class AppTest {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        PlainBean plainBean = (PlainBean)ctx.getBean("plainBean");
        String txt = plainBean.say("arac");
        System.out.println(txt);
        System.out.println(plainBean.getId());
        System.out.println(plainBean);
        plainBean = (PlainBean)ctx.getBean("plainBean");
        System.out.println(plainBean);
    }
}
