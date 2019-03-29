package com.kotall.learn.spring.samples.bean;

import com.kotall.learn.spring.samples.service.WeatherService;
import com.kotall.learn.spring.samples.service.WeatherServiceImpl;
import org.springframework.beans.factory.FactoryBean;

/**
 * 描述：
 *
 * @author: zpwang
 * @time: 2019/3/24 20:36
 */
public class WeatherFactoryBean implements FactoryBean<WeatherService> {

    @Override
    public WeatherService getObject() throws Exception {
        return new WeatherServiceImpl();
    }

    @Override
    public Class<?> getObjectType() {
        return WeatherService.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
