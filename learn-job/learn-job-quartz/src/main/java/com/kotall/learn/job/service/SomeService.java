package com.kotall.learn.job.service;

import org.springframework.stereotype.Service;

/**
 * @author : zpwang
 * @version : 1.0.0
 * @date : 2018/3/29
 */
@Service("someService")
public class SomeService {

    public void writeDataToLog(String dataToWrite) {
        System.out.println(dataToWrite);
    }

}
