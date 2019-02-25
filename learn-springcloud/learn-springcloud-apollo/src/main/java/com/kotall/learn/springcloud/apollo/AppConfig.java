package com.kotall.learn.springcloud.apollo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * desc:
 *
 * @author zpwang
 * @create 2019/2/25 13:34
 * @since 1.0.0
 */
@Configuration

public class AppConfig {

    @Value("${username}")
    private String username;
    @Value("${username}")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
