package com.kotall.learn.docker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * desc:
 *
 * @author zpwang
 * @date 2020/1/10 9:38
 * @since 1.0.0
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "{\n" +
                "\t\"say\": \"Hello,Alpin\"\n" +
                "}";
    }
}
