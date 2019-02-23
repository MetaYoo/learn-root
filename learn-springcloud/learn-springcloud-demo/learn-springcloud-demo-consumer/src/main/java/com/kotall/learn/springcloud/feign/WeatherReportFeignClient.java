package com.kotall.learn.springcloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author zpwang
 * @version 1.0.0
 */
@FeignClient(name="producer-server", fallback = WeatherReportFeignClientHystrix.class)
public interface WeatherReportFeignClient {

    @RequestMapping(value = "/report/{id}",method = RequestMethod.GET)
    String report(@PathVariable("id") String id);
}
