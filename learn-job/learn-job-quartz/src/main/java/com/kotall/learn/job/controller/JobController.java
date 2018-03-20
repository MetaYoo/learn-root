package com.kotall.learn.job.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : zpwang
 * @version : 1.0.0
 * @date : 2018/3/20
 */
@RestController
@RequestMapping("/job")
public class JobController {


    @RequestMapping("/start")
    public String start() {

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
