package com.kotall.learn.job.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author : zpwang
 * @version : 1.0.0
 * @date : 2018/3/20
 */
@Slf4j
@Component("simpleJob")
public class SimpleJob implements Job {

    public SimpleJob() {
       log.info("========================= create SimpleJob ===========================");
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        DateTimeFormatter fmt = DateTimeFormatter.ISO_DATE_TIME;
        System.out.println(fmt.format(LocalDateTime.now()) + ": hello, quartz job!");
    }
}
