package com.kotall.learn.spring.samples.service;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class LogServiceImpl implements LogService {
    @Override
    public void addLog(String logText) {
        System.out.println("log info ==> " + logText);
    }
}
