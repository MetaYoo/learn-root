package com.kotall.learn.rbac.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述：
 *
 * @author: zpwang
 * @time: 2019/5/19 15:37
 */
@Controller
@RequestMapping("/")
public class AdminController {

    @RequestMapping("/{module}/{page}.html")
    public String loginPage(@PathVariable("module") String module, @PathVariable("page") String page) {
        return module + "/" + page +".html";
    }

    @RequestMapping("/admin/index")
    public String index() {
        return "admin/index";
    }

    @RequestMapping("/admin/logout")
    @ResponseBody
    public String logout() {
        return "logout";
    }


    @RequestMapping("/admin/code")
    @ResponseBody
    public String code(HttpServletRequest request, HttpServletResponse response) {
        String code = "123456";
        request.getSession().setAttribute("code", code);
        return code;
    }
}
