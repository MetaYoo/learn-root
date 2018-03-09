package com.kotall.learn.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/3/8 0008 下午 10:13
 */
@RestController
public class HelloWorldController {

    @RequestMapping(value = "/hello", produces = "application/json")
    public String hello() {
        return "{'msg':'hello world !'}";
    }
}
