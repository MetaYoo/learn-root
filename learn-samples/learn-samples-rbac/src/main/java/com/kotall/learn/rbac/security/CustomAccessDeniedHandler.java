package com.kotall.learn.rbac.security;

import com.alibaba.fastjson.JSON;
import com.kotall.learn.rbac.common.Result;
import com.kotall.learn.rbac.common.utils.HttpKit;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 描述：
 *
 * @author: zpwang
 * @time: 2019/5/14 23:15
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e)
            throws IOException, ServletException {
        if (HttpKit.isAjaxRequest(request)) {
           response.setHeader("ContentType", MediaType.APPLICATION_JSON_UTF8_VALUE);
           PrintWriter out = response.getWriter();
           out.print(JSON.toJSONString(Result.denied()));
           out.flush();
        }
    }
}
