package com.kotall.learn.springboot.api;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * desc:
 *
 * @author zpwang
 * @date 2019/3/27 14:53
 * @since 1.0.0
 */
@RestController
public class DemoController {

    @GetMapping("/demo")
    public String demo() {
        int a = 10 / 0;
        return "";
    }

    @ExceptionHandler(Exception.class)
    public String localExHandler(Exception e) {
        return "local ex handler";
    }
}
