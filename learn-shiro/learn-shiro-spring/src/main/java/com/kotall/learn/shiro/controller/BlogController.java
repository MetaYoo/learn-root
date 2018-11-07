package com.kotall.learn.shiro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zpwang
 * @version 1.0.0
 */
@RestController
@RequestMapping("/blog")
public class BlogController {

    @GetMapping("/home")
    public String home() {
        return "home";
    }


}
