package com.kotall.learn.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * desc:
 *
 * @author zpwang
 * @date 2019/11/25 10:57
 * @since 1.0.0
 */
@EnableDiscoveryClient
@SpringBootApplication
public class DiscoveryProviderServer {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryProviderServer.class, args);
    }
}
