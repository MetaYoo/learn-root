package com.kotall.learn.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 描述：
 *
 * @author: zpwang
 * @time: 2019/5/12 9:02
 */
@Controller
public class PageController {

    /**
     * 匹配 x.html
     *
     * @param page
     * @return
     */
    //@RequestMapping("/{page}.jhtml")
    public String page(@PathVariable("page") String page) {
        System.out.println("====>" + page);
        return page;
    }

    /**
     * 匹配 'xx/xx.html'
     *
     * @param dic
     * @param page
     * @return
     */
    //@RequestMapping("/{dic}/{page}.jhtml")
    public String deepPage(@PathVariable("dic") String dic, @PathVariable("page") String page) {
        System.out.println("====>" + dic + "/" + page);
        return dic + "/" + page;
    }


}
