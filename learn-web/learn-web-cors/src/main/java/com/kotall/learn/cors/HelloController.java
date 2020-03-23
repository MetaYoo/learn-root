package com.kotall.learn.cors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * desc:
 *
 * @author zpwang
 * @date 2020/3/23 10:20
 * @since 1.0.0
 */
@RequestMapping("/hello")
@RestController
public class HelloController {

    @GetMapping("/info")
    @ResponseBody
    public Map<String, String> info() {
        System.out.println("========info=======");
        Map map = new HashMap(16);
        map.put("name", "kitty");
        map.put("age", "18");
        return map;
    }

}
