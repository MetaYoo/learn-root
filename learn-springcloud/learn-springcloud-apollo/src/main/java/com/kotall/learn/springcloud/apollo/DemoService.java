package com.kotall.learn.springcloud.apollo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * desc:
 *
 * @author zpwang
 * @create 2019/2/25 13:36
 * @since 1.0.0
 */
@RestController
public class DemoService {
    @Autowired
    private AppConfig appConfig;

    @RequestMapping("/prop")
    public String getProp(String key) {
        return appConfig.getUsername();
    }


}
