package com.kotall.learn.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * desc:
 *
 * @author zpwang
 * @date 2019/11/25 11:21
 * @since 1.0.0
 */
@EnableFeignClients
@SpringBootApplication
public class NacosDiscoveryConsumerServer {

    public static void main(String[] args) {
        SpringApplication.run(NacosDiscoveryConsumerServer.class, args);
    }
}
