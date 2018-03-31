package com.kotall.learn.job.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author : zpwang
 * @version : 1.0.0
 * @date : 2018/3/29
 */
@Slf4j
@Service("someService")
public class HealthCheckService {

    public void check() {
        log.info("everything is ok!");
    }

}
