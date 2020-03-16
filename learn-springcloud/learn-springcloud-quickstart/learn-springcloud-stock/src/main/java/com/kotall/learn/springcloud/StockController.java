package com.kotall.learn.springcloud;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * desc:
 *
 * @author zpwang
 * @date 2020/3/16 16:00
 * @since 1.0.0
 */
@RequestMapping("/stock")
@RestController
public class StockController {

    @GetMapping("/info")
    public ResponseEntity<String> info(@RequestParam(name = "id") String id) {
        ResponseEntity resp = new ResponseEntity(id, HttpStatus.OK);
        System.out.println(resp);
        return resp;
    }

}
