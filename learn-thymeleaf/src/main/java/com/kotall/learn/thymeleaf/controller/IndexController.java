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

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("name", "知识林");
        return "/web/index";
    }


}
