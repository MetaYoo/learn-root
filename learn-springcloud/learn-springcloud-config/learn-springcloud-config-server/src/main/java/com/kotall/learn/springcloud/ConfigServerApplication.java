package com.kotall.learn.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/3/18 0018 上午 11:08
 */
@SpringBootApplication
@RestController
public class ConfigServerApplication {

    @RequestMapping("/")
    public String home() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class);
    }
}
