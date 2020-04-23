package com.kotall.learn.springcloud.alibaba;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
@RefreshScope
public class NacosConfigController {


    @Value("${user:aracwong}")
    private String user;

    @RequestMapping("/get")
    public String get() {
        return user;
    }
}
