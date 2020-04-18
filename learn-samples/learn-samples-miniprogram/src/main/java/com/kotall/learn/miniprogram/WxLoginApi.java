package com.kotall.learn.miniprogram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class WxLoginApi {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/login")
    public Map<String, Object> login(@RequestParam("code") String code) {
        Map<String, Object> result = new HashMap<>();
        System.out.println(code);
        // adfas
        // sdfasdfsa
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wx53ecc1ddafsadf2b313d61d&secret=dsafasd&js_code=" + code + "&grant_type=authorization_code";
        String res = restTemplate.getForObject(url, String.class);
        System.out.println(res);

        res = restTemplate.getForObject("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=sdafsfs&secret=sdfsd", String.class);
        System.out.println(res);

        return result;
    }
}
