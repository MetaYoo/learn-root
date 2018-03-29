package com.kotall.learn.job;

import com.kotall.learn.job.job.MySimpleJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author : zpwang
 * @version : 1.0.0
 * @date : 2018/3/20
 */
public class QuartzWithCronTriggerTest {

    public static void main(String[] args) throws Exception {
        // job
        JobDetail job = JobBuilder.newJob(MySimpleJob.class).withIdentity("job1", "group1").build();

        // trigger
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ? *"))
                .build();

        // schedule
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.scheduleJob(job, trigger);

        System.out.println("======================" + DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now()) + "=======================");
        scheduler.start();
    }

}
