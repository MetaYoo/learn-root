package com.kotall.learn.shiro.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/3/15 0015 下午 6:22
 */
@RestController
@Slf4j
public class BlogController {

    @GetMapping("/article/{id}")
    public String article(@PathVariable("id") String aid) {
        log.info("====== get article by id:{}", aid);
        return "hello world";
    }
}
