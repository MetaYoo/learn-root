package com.kotall.learn.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/3/8 0008 下午 10:13
 */
@EnableAutoConfiguration
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args).start();
    }
}
