package com.kotall.learn.springcloud.feign;

import org.springframework.stereotype.Component;

/**
 * @author zpwang
 * @version 1.0.0
 */
@Component
public class WeatherReportFeignClientHystrix implements WeatherReportFeignClient {

    @Override
    public String report(String id) {
        return "天气预报服务调用失败！";
    }
}
