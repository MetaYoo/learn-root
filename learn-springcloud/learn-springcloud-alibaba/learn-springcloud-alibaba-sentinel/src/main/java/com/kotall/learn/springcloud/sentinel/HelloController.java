package com.kotall.learn.springcloud.sentinel;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HelloController {

    @Resource
    HelloService helloService;


    @RequestMapping("/hello")
    @SentinelResource(value = "hello-sentinel", blockHandler = "handleException", blockHandlerClass = {ExceptionUtil.class})
    public String hello() {
        System.out.println("===============");
        return "Hello World Sentinel";
    }

    public class ExceptionUtil {
        public String handleException(BlockException ex) {
            return "自定义限流逻辑埋点";
        }
    }
}
