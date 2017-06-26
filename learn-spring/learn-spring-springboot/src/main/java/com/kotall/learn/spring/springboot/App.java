package com.kotall.learn.spring.springboot;/**
 * Created by Administrator on 2017/6/26 0026.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author aracwong
 * @version 1.0.0
 * @email
 * @datetime 2017/6/26 0026 下午 2:12
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
