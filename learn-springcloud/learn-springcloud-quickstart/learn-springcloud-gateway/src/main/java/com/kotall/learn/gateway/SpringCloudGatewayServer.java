package com.kotall.learn.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * desc:
 *
 * @author zpwang
 * @date 2020/3/16 10:53
 * @since 1.0.0
 */
@SpringBootApplication
public class SpringCloudGatewayServer {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudGatewayServer.class, args);
    }

//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder
//                .routes()
//                .route(r -> r
//                        .uri(""))
//                .build();
//    }
}