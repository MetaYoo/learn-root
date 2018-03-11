package com.kotall.learn.springcloud.eureka.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import java.util.Scanner;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/3/11 0011 下午 3:46
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaClusterServer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String profile = scanner.nextLine();
        new SpringApplicationBuilder(EurekaClusterServer.class)
                .profiles(profile).web(true).run(args);
    }
}
