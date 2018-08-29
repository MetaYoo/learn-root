package com.kotall.learn.oauth2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicController {

    @RequestMapping("/abc")
    public String index() {
        return "Hello abc";
    }


    @RequestMapping("/login")
    public String login() {
        return "Hello oauth2";
    }
 
}