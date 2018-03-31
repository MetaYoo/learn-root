package com.kotall.learn.job.job;

import com.kotall.learn.job.service.HealthCheckService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/3/31 0031 上午 11:51
 */
@Component
public class SpringJob implements Job {

    @Autowired
    private HealthCheckService healthCheckService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        this.healthCheckService.check();
    }
}
