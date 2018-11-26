package com.kotall.learn.spring.samples.bean;

import org.springframework.beans.factory.InitializingBean;

/**
 * Bean 初始化顺序： 构造方法 -> BeanPostProcessor -> InitializingBean -> Bean中的初始化方法 init-method.
 * @author zpwang
 * @version 1.0.0
 */
public class HelloBean implements InitializingBean {

    private String name;

    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public HelloBean() {
        System.out.println(" 构造方法");
    }

    public void init() {
        System.out.println(" init ");
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        System.out.println(" afterPropertiesSet ");
    }
}
