package com.kotall.learn.job;

import com.kotall.learn.job.job.SimpleJob;
import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author : zpwang
 * @version : 1.0.0
 * @date : 2018/3/20
 */
public class QuartzWithSimpleScheduleTest {


    @Test
    public void testJob1() throws Exception {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        scheduler.start();

    }

    public static void main(String[] args) throws Exception {
        // job
        JobDetail job = JobBuilder.newJob(SimpleJob.class).withIdentity("job1", "group1").build();

        // trigger
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(120).repeatForever())
                .build();

        // schedule
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.scheduleJob(job, trigger);

        System.out.println("======================" + DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now()) + "=======================");
        scheduler.start();
    }

}
