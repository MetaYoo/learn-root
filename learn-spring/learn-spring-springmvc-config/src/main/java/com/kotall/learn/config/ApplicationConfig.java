package com.kotall.learn.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@ComponentScan(value = "com.kotall.learn",
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class),
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = RestController.class)})
public class ApplicationConfig {
    /**
     * 在此配置除了 Controller 之外的其他Bean，比如数据库连接池，事务管理器等其他业务Bean
     */


}
