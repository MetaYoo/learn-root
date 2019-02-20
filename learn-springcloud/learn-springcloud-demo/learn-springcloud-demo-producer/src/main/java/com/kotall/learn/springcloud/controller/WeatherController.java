package com.kotall.learn.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zpwang
 * @version 1.0.0
 */
@RestController
public class WeatherController {

    @GetMapping("/report/{city}")
    String report(@PathVariable("city") String city) {
        return city + " 的天气是：晴天！";
    }
}
