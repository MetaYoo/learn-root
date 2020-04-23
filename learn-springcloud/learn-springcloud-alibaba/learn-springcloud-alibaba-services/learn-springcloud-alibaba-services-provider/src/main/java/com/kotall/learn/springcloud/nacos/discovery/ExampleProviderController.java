package com.kotall.learn.springcloud.nacos.discovery;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ExampleProviderController {


    @GetMapping("/pay")
    public String pay(@RequestParam("account") String account, @RequestParam("amt") Integer amt) {
        log.info(account = "->" + amt);
        return account + "->" + amt;
    }
}
