package com.kotall.learn.thymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author : zpwang
 * @version : 1.0.0
 * @date : 2018/1/8
 */
@Controller
public class IndexController {

    @Autowired
    LocaleResolver localeResolver;

    /**
     * 1. hello world
     *    获取变量值
     * @param model
     * @return
     */
    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("name", "知识林");
        return "/hello";
    }


    /**
     * 2. URL
     *    引入URL
     * @param model
     * @return
     */
    @RequestMapping(value = "url", method = RequestMethod.GET)
    public String url(Model model) {
    	model.addAttribute("id", 100);
        return "/url";
    }

    /**
     * 3. MSG
     *    消息
     * @param model
     * @return
     */
    @RequestMapping(value = "msg", method = RequestMethod.GET)
    public String msg(Model model, HttpServletRequest request, HttpServletResponse response) {
        localeResolver.setLocale(request, response, Locale.ENGLISH);
        model.addAttribute("name", "aracwong");
        model.addAttribute("user", "admin");
        model.addAttribute("today", new Date());
        return "msg";
    }

    /**
     * 3. MSG
     *    消息
     * @param model
     * @return
     */
    @RequestMapping(value = "unknow", method = RequestMethod.GET)
    public String unknow(Model model) {
        return "unknow";
    }

    /**
     * 4. param
     *    消息
     * @param model
     * @return
     */
    @RequestMapping(value = "param", method = RequestMethod.GET)
    public String param(Model model) {
        return "param";
    }

    /**
     * 5. list
     *    循环
     * @param model
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
    	List<String> list = new ArrayList<String>();
    	list.add("a");
    	list.add("b");
    	list.add("c");
    	list.add("d");
    	model.addAttribute("list", list);
        return "list";
    }
    
    /**
     * 6. fragment
     *    循环
     * @param model
     * @return
     */
    @RequestMapping(value = "frag", method = RequestMethod.GET)
    public String fragment(Model model) {
    	model.addAttribute("arg1", "arac1");
    	model.addAttribute("arg2", "arac2");
        return "frag";
    }
    

}
