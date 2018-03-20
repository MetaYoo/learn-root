package com.kotall.learn.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author : zpwang
 * @version : 1.0.0
 * @date : 2018/3/20
 */
public class JobWithJobData implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String name = jobDataMap.getString("name");
        int age = jobDataMap.getInt("age");
        DateTimeFormatter fmt = DateTimeFormatter.ISO_DATE_TIME;
        System.out.println(fmt.format(LocalDateTime.now()) + ": hello, quartz job! job data: name=" + name + "& age=" + age);
    }
}
