package com.kotall.learn.job.job;

import com.kotall.learn.job.service.SomeService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author : zpwang
 * @version : 1.0.0
 * @date : 2018/3/29
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class JobRunner implements Job {

    private String dataToWrite;

    @Autowired
    private SomeService someService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        someService.writeDataToLog(dataToWrite);
    }

    public void setDataToWrite(String dataToWrite) {
        this.dataToWrite = dataToWrite;
    }
}
