package com.kotall.learn.rbac.security;

import com.alibaba.fastjson.JSON;
import com.kotall.learn.rbac.common.Result;
import com.kotall.learn.rbac.common.utils.HttpKit;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
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
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException, ServletException {
        if (HttpKit.isAjaxRequest(request)) {
            response.setHeader("ContentType", MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setCharacterEncoding("UTF-8");
            Result result = new Result();
            result.setCode(501);
            result.setMsg(e.getMessage());
            PrintWriter out = response.getWriter();
            out.print(JSON.toJSONString(result));
            out.flush();
        }

    }
}
