package com.kotall.learn.springcloud.eureka.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/3/10 0010 下午 10:42
 */
@RestController
@Slf4j
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/router")
    public String router() {
        log.info("============router");
        String json = restTemplate.getForObject("http://first-eureka-client-provider:8081/call/1", String.class);
        return json;
    }
}
