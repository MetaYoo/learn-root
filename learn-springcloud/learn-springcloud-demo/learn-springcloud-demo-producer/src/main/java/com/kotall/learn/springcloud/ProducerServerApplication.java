package com.kotall.learn.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zpwang
 * @version 1.0.0
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ProducerServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerServerApplication.class, args);
    }
}
