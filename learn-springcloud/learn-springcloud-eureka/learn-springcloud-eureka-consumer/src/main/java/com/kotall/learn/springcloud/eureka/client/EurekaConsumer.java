package com.kotall.learn.springcloud.eureka.client;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/3/10 0010 下午 10:36
 */
@SpringBootApplication
@EnableEurekaClient
public class EurekaConsumer {

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
    public static void main(String[] args) {
        new SpringApplicationBuilder(EurekaConsumer.class).web(true).run(args);
    }
}
