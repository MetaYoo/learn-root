package com.kotall.learn.cloud.samples;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：
 *
 * @author: zpwang
 * @time: 2019/7/8 21:01
 */
@RestController
public class AccountController {


    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
