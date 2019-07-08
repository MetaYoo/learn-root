package com.kotall.learn.cloud.samples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 描述：
 *
 * @author: zpwang
 * @time: 2019/7/8 22:08
 */
@EnableEurekaClient
@SpringBootApplication
public class OrderServer {
    public static void main(String[] args) {
        SpringApplication.run(OrderServer.class, args);
    }
}
