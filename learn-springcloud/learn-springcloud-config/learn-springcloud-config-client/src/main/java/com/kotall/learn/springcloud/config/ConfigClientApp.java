package com.kotall.learn.springcloud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aracwong
 */
@SpringBootApplication
@RestController
public class ConfigClientApp {

    @Value("${city}")
    String city;

    @Value("${user.name}")
    String userName;

    @RequestMapping(value = "/city")
    public String city(){
        return this.city + "----" + userName;
    }

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApp.class, args);
    }
}
