package com.kotall.learn.springcloud.sentinel.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ServiceProviderServer {
    public static void main(String[] args) {
        SpringApplication.run(ServiceProviderServer.class, args);
    }
}
