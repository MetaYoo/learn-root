package com.kotall.learn.springcloud.eureka.client;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.Scanner;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/3/7 0007 下午 10:17
 */
@EnableEurekaClient
@SpringBootApplication
public class EurekaClusterProvider {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String profile = scanner.nextLine();
        new SpringApplicationBuilder(EurekaClusterProvider.class)
                .profiles(profile)
                .web(true)
                .run(args);
    }
}
