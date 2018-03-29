package com.kotall.learn.job.config;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * @author : zpwang
 * @version : 1.0.0
 * @date : 2018/3/29
 */
@Component
public class JobProperties {
    @NotNull
    @NotEmpty
    private String cronExpression;

    @NotNull
    @NotEmpty
    private String dataToWrite;

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getDataToWrite() {
        return dataToWrite;
    }

    public void setDataToWrite(String dataToWrite) {
        this.dataToWrite = dataToWrite;
    }
}
