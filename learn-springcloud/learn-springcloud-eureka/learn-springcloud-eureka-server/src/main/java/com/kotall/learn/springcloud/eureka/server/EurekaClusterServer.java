package com.kotall.learn.springcloud.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/3/11 0011 下午 3:46
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaClusterServer {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer.class, args);
    }
}
