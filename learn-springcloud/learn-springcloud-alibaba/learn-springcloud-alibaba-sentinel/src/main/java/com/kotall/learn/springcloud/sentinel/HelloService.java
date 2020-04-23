package com.kotall.learn.springcloud.sentinel;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    @SentinelResource("test")
    public String hello() {
        return "hello world sentinel";
    }
}
