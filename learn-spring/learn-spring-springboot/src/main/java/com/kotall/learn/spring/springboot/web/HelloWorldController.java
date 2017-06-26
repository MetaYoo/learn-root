package com.kotall.learn.spring.springboot.web;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aracwong
 * @version 1.0.0
 * @email
 * @datetime 2017/6/24 0024 下午 5:52
 */
@SpringBootApplication
@RestController
public class HelloWorldController {
    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }
}
