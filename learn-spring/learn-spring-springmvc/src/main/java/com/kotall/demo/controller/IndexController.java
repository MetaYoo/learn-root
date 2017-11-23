package com.kotall.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: aracwong
 * @email: aracwong@163.com
 * @datetime: 2017/11/19 0019 下午 9:22
 * @version: 1.0.0
 */
@Controller
@RequestMapping("/")
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username, String password) {
        LOGGER.info("login with username={},password={}", username, password);
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}
