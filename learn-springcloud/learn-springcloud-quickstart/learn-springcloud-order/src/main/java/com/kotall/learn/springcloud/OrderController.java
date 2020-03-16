package com.kotall.learn.springcloud;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * desc:
 *
 * @author zpwang
 * @date 2020/3/16 11:43
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/order")
public class OrderController {

    @GetMapping(value = "/cookie")
    public ResponseEntity<String> cookie(@CookieValue(name = "doge") String doge) {
        System.out.println("cookie => doge=" + doge);
        return ResponseEntity.ok(doge);
    }
}
