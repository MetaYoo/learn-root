package com.kotall.learn.spring.samples.service;

/**
 * 描述：
 *
 * @author: zpwang
 * @time: 2019/3/24 20:37
 */
public class WeatherServiceImpl implements WeatherService {
    @Override
    public void report(String city) {
        System.out.println(city + " is sunny");
    }
}
