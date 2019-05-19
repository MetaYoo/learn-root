package com.kotall.learn.rbac.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 描述：
 *
 * @author: zpwang
 * @time: 2019/5/19 15:37
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/index")
    public String index() {

        return "success";
    }
}
