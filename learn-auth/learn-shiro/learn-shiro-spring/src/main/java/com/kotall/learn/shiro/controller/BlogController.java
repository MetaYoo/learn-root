package com.kotall.learn.shiro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    /**
     * 页面跳转
     * @param module
     * @param url
     * @return
     */
    @RequestMapping("{module}/{url}.html")
    public String page(@PathVariable("module") String module, @PathVariable("url") String url) {
        System.out.println("=======================");
        return module + "/" + url;
    }
}
