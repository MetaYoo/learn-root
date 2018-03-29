package com.kotall.learn.job;

import org.quartz.JobDetail;
import org.quartz.Trigger;

/**
 * @author : zpwang
 * @version : 1.0.0
 * @date : 2018/3/29
 */
public class JobScheduleModel {

    private JobDetail jobDetail;
    private Trigger trigger;

    public JobScheduleModel(JobDetail jobDetail, Trigger trigger) {
        this.jobDetail = jobDetail;
        this.trigger = trigger;
    }

    public JobDetail getJobDetail() {
        return jobDetail;
    }

    public Trigger getTrigger() {
        return trigger;
    }
}
