package com.kotall.learn.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zpwang
 * @version 1.0.0
 */
@SpringBootApplication
//@EnableAuthorizationServer
public class AlanOAuthApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AlanOAuthApplication.class, args).start();
    }

}
