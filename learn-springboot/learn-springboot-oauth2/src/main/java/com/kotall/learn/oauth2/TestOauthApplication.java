package com.kotall.learn.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

/**
 * @author zpwang
 * @version 1.0.0
 */

@SpringBootApplication
@EnableOAuth2Sso
public class TestOauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestOauthApplication.class, args);
    }

}
