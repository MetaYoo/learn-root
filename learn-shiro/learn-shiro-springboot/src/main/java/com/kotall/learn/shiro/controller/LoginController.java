package com.kotall.learn.shiro.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/3/15 0015 下午 6:18
 */
@RestController
@Slf4j
public class LoginController {

    @GetMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password")String password) {
        log.info("======username:{}, password:{}", username, password);
        return "success";
    }
}
