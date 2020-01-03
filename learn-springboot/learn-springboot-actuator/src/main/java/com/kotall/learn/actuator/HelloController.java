package com.kotall.learn.actuator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * desc:
 *
 * @author zpwang
 * @date 2020/1/3 14:41
 * @since 1.0.0
 */
@RestController
@RequestMapping("/")
public class HelloController {

    @MethodMetric(name = "hello-demo")
    @GetMapping("/hello")
    public String hello() {
        Random random = new Random();
        try {
            TimeUnit.SECONDS.sleep(random.nextInt(10));
        } catch (InterruptedException e) {
            return "error";
        }
        return "hello";
    }
}
