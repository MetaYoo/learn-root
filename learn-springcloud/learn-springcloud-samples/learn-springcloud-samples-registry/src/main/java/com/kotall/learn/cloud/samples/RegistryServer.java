package com.kotall.learn.cloud.samples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 描述：
 *
 * @author: zpwang
 * @time: 2019/7/8 21:06
 */
@EnableEurekaServer
@SpringBootApplication
public class RegistryServer {

    public static void main(String[] args) {
        SpringApplication.run(RegistryServer.class, args);
    }
}
