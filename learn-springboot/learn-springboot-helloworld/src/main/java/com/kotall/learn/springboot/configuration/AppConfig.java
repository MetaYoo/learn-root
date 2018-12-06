package com.kotall.learn.springboot.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : zpwang
 * @version : 1.0.0
 * @date : 2018/3/10
 */
@Component
@ConfigurationProperties(prefix = "spring.boot")
public class AppConfig {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
