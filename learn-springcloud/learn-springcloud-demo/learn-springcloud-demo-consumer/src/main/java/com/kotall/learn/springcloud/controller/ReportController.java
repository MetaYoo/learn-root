package com.kotall.learn.springcloud.controller;

import com.kotall.learn.springcloud.feign.WeatherReportFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author zpwang
 * @version 1.0.0
 */
@RestController
public class ReportController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WeatherReportFeignClient weatherReportFeignClient;

    @GetMapping("/test/{id}")
    public String test(@PathVariable String id){
        return this.restTemplate.getForObject("http://producer-server/report/"+id, String.class);
    }

    @GetMapping("/report/{id}")
    public String test1(@PathVariable String id){
        return this.weatherReportFeignClient.report(id);
    }


}
