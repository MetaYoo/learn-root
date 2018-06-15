package com.kotall.learn.shiro;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


/**
 * Hello world!
 */
@SpringBootApplication
public class ShiroDemoApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ShiroDemoApp.class).web(true).run(args);
    }
}
