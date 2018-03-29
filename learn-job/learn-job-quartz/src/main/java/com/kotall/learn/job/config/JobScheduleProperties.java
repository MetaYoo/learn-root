package com.kotall.learn.job.config;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author : zpwang
 * @version : 1.0.0
 * @date : 2018/3/29
 */
@ConfigurationProperties(prefix = "schedule")
public class JobScheduleProperties {

    @NotNull
    @NotEmpty
    @Valid
    private List<JobProperties> jobs;

    public List<JobProperties> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobProperties> jobs) {
        this.jobs = jobs;
    }
}
