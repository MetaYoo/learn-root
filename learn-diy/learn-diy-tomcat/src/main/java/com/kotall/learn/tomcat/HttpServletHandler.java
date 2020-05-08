package com.kotall.learn.tomcat;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class HttpServletHandler {

    public void handle(ServletRequest req, ServletResponse resp) {
        try {
            System.out.println("===========================================");
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().print("Hello World!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
