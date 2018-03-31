package com.kotall.learn.job.controller;

import com.kotall.learn.job.job.SimpleJob;
import com.kotall.learn.job.job.SpringJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : zpwang
 * @version : 1.0.0
 * @date : 2018/3/20
 */
@Slf4j
@RestController
@RequestMapping("/springjob")
public class SpringJobController {

    private static final String JOB_GROUP = "default_job_group";
    private static final String TRIGGER_GROUP = "default_trigger_group";

    @Autowired
    private Scheduler scheduler;

    @RequestMapping("/start")
    public String start() {
        try {

            JobDetail jobDetail = JobBuilder.newJob(SpringJob.class).withIdentity("SIMPLE_JOB", JOB_GROUP).build();

            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("SIMPLE_TRIGGER", TRIGGER_GROUP).startNow().build();

            scheduler.scheduleJob(jobDetail, trigger);

            log.info("simple job started.");
        } catch (Exception e) {
            log.error("failed to start job", e);
        }

        return "start success";
    }

    @RequestMapping("/pause")
    public String pause() {

        return "pause success";
    }

    @RequestMapping("/stop")
    public String stop() {

        return "stop success";
    }


    @RequestMapping("/resume")
    public String resume() {

        return "resume success";
    }


}
