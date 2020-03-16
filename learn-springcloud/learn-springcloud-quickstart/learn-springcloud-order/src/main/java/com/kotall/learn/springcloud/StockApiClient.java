package com.kotall.learn.springcloud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * desc:
 *
 * @author zpwang
 * @date 2020/3/16 16:04
 * @since 1.0.0
 */
@FeignClient(name = "springcloud-server-stock")
public interface StockApiClient {

    /**
     * inifo
     *
     * @param id
     * @return
     */
    @GetMapping(path = "/stock/info")
    @ResponseBody
    ResponseEntity<String> info(@RequestParam(name = "id") String id);
}
