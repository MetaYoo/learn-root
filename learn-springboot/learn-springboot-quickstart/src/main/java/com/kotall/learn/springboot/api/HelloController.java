package com.kotall.learn.springboot.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zpwang
 * @version 1.0.0
 */
@RestController
public class HelloController {

    @GetMapping("/{param}")
    public String hello(@PathVariable("param") String param) {
        return param;
    }
}
