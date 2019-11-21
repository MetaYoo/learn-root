package com.kotall.learn.quartz.schedule;

import com.kotall.learn.quartz.job.SimpleJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * desc:
 *
 * @author zpwang
 * @date 2019/11/21 11:56
 * @since 1.0.0
 */
@Component
public class SampleScheduler {

    @Bean
    public JobDetail sampleJobDetail() {
        // 链式编程,可以携带多个参数,在Job类中声明属性 + setter方法
        return JobBuilder.newJob(SimpleJob.class)
                .withIdentity("sampleJob", "group1")
                .usingJobData("name", "World")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger sampleJobTrigger() {
        // 每隔两秒执行一次
        SimpleScheduleBuilder scheduleBuilder =
                SimpleScheduleBuilder
                        .simpleSchedule()

                        .withIntervalInSeconds(10)
                        .repeatForever();
        return TriggerBuilder.newTrigger()
                .forJob(sampleJobDetail())
                .withIdentity("sampleTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }
}
