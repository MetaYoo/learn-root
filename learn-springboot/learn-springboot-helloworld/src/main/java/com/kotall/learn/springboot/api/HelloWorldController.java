package com.kotall.learn.springboot.api;

import com.kotall.learn.springboot.configuration.AppConfig;
import com.kotall.learn.springboot.entity.UserEntity;
import com.kotall.learn.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/3/8 0008 下午 10:13
 */
@RestController
public class HelloWorldController {

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/hello", produces = "application/json")
    public String hello() {
        System.out.println("========name=" + appConfig.getName() + "; age=" + appConfig.getAge());
        return "{'msg':'hello world !'}";
    }

    @RequestMapping(value = "/err")
    public String error() {

        return "" + 1/0;
    }

    @RequestMapping(value = "/useradd")
    public String addUser(String name, int age) {
        UserEntity user = new UserEntity();
        user.setUsername(name);
        user.setAge(age);
        this.userService.save(user);
        return "{'msg': 'success'}";
    }
}
