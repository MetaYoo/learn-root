package com.kotall.learn.controller;

import com.kotall.learn.bean.User;
import com.kotall.learn.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zpwang
 * @version 1.0.0
 */
@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/index")
    public String index(HttpServletRequest request) {
        String token = request.getHeader("Authorization").split(" ")[1].trim();

        User user = new User();
        user.setPassword("123456");
        Claims claims = JwtUtil.parseJWT(token, user);
        System.out.println(claims.get("id"));
        System.out.println(claims.get("username"));
        System.out.println(claims.get("password"));
        return "index";
    }
}
