package com.kotall.learn.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author : zpwang
 * @version : 1.0.0
 * @date : 2018/1/8
 */
@Controller
public class IndexController {

    /**
     * 1. hello world
     *    获取变量值
     * @param model
     * @return
     */
    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("name", "知识林");
        return "/web/hello";
    }


    /**
     * 2. URL
     *    引入URL
     * @param model
     * @return
     */
    @RequestMapping(value = "url", method = RequestMethod.GET)
    public String url(Model model) {
        return "/web/url";
    }

    /**
     * 3. MSG
     *    消息
     * @param model
     * @return
     */
    @RequestMapping(value = "msg", method = RequestMethod.GET)
    public String msg(Model model) {
        model.addAttribute("name", "aracwong");
        return "web/msg";
    }



}
