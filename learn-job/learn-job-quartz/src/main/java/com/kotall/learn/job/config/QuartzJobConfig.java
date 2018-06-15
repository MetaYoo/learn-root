package com.kotall.learn.job.config;

import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/3/31 0031 上午 10:53
 */
@Configuration
public class QuartzJobConfig {

    /** 可以在 具体的Job 中注入spring容器的bean */
    @Autowired
    private SpringJobFactory springJobFactory;

    @Bean
    public Scheduler scheduler() {
        return this.schedulerFactoryBean().getScheduler();
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobFactory(springJobFactory);
        return schedulerFactoryBean;
    }


}
