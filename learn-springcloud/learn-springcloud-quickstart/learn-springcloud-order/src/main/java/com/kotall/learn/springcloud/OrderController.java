package com.kotall.learn.springcloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private StockApiClient stockApiClient;

    @GetMapping(value = "/cookie")
    public ResponseEntity<String> cookie(@CookieValue(name = "doge") String doge) {
        System.out.println("cookie => doge=" + doge);
        return ResponseEntity.ok(doge);
    }

    @GetMapping("/info")
    public ResponseEntity info(@RequestParam(name = "id") String id) {
        ResponseEntity resp = this.stockApiClient.info(id);
        System.out.println(resp.getBody());
        return resp;
    }


}
