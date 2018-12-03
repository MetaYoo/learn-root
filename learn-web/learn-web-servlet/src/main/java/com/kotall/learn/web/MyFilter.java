package com.kotall.learn.web;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("======= myFilter initialized ======");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("======= myFilter doFilter ======");
    }

    @Override
    public void destroy() {
        System.out.println("======= myFilter initialized ======");
    }
}
