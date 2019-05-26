package com.kotall.learn.rbac.security;

import com.alibaba.fastjson.JSON;
import com.kotall.learn.rbac.common.Result;
import com.kotall.learn.rbac.common.Token;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 描述：
 *
 * @author: zpwang
 * @time: 2019/5/14 23:13
 */
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        // 登录成功 设置Cookie
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Token token = new Token();
        token.setUsername(userDetails.getUsername());
        response.setCharacterEncoding("UTF-8");
        Cookie cookie = new Cookie("auth", JSON.toJSONString(token));
        response.addCookie(cookie);
        PrintWriter out = response.getWriter();
        Result result = Result.ok();
        out.write(JSON.toJSONString(result));
        out.flush();

    }
}
