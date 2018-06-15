package com.kotall.learn.shiro.ssl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/4/14 0014 下午 2:52
 */
@RestController
public class HelloController {

    @GetMapping("/index")
    public String index() {
        return "hello world";
    }
}
