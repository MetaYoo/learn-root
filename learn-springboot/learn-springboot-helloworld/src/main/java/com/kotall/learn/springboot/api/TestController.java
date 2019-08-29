package com.kotall.learn.springboot.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * desc:
 *
 * @author zpwang
 * @date 2019/8/29 17:44
 * @since 1.0.0
 */
@RestController
@RequestMapping("/")
public class TestController {
    protected HttpServletRequest req;

    protected HttpServletResponse resp;

    @ModelAttribute
    protected void setHttpObjects(HttpServletRequest request,HttpServletResponse response){
        this.req = request;
        this.resp = response;
    }

    @GetMapping("/test")
    public String test() {
        String name = req.getParameter("name");
        Integer age = Integer.parseInt(req.getParameter("age"));
        System.out.println(name + "=" + age);
        return name + "=" + age;
    }



}
