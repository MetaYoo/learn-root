package com.kotall.learn.springboot.controller;

import com.kotall.learn.springboot.configuration.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/3/8 0008 下午 10:13
 */
@RestController
public class HelloWorldController {

    @Autowired
    AppConfig appConfig;

    @RequestMapping(value = "/hello", produces = "application/json")
    public String hello() {
        System.out.println("========name=" + appConfig.getName() + "; age=" + appConfig.getAge());
        return "{'msg':'hello world !'}";
    }
}
