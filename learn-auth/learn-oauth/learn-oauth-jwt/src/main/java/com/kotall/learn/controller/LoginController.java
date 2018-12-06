package com.kotall.learn.controller;

import com.kotall.learn.bean.User;
import com.kotall.learn.jwt.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zpwang
 * @version 1.0.0
 */
@RestController
@RequestMapping("/")
public class LoginController {

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password
            , HttpServletRequest request, HttpServletResponse response) {

        User user = new User();
        user.setId(1);
        user.setUsername("arac");
        user.setPassword("123456");
        String token = JwtUtil.createJWT(10000000,user);

        response.addHeader("Authorization", "Bearer " + token);
        return "hello";
    }
}
