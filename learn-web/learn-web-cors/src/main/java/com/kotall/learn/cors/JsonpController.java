package com.kotall.learn.cors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * desc:
 *
 * @author zpwang
 * @date 2020/3/23 11:13
 * @since 1.0.0
 */
@RequestMapping("/jsonp")
@Controller
public class JsonpController {

    @GetMapping("/info")
    @ResponseBody
    public String info(HttpServletRequest request) {
        String callback = request.getParameter("callback");
        String rs = callback + "('" + "hello" + "')"  ;
        return rs;
    }
}
