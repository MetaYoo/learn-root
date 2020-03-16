package com.kotall.learn.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * desc:
 *
 * @author zpwang
 * @date 2020/3/16 15:01
 * @since 1.0.0
 */
@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudStockServer {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudStockServer.class, args);
    }
}
