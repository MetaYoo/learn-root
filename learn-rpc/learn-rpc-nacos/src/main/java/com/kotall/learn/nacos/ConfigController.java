package com.kotall.learn.nacos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * desc:
 *
 * @author zpwang
 * @date 2019/11/22 18:51
 * @since 1.0.0
 */
@Controller
@RequestMapping("/config")
public class ConfigController {

    @Value("${content:123}")
    private String useLocalCache;

    public void setUseLocalCache(String useLocalCache) {
        this.useLocalCache = useLocalCache;
    }

    @RequestMapping(value = "/get", method = GET)
    @ResponseBody
    public String get() {
        return useLocalCache;
    }
}
