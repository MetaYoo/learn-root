package com.kotall.learn.shiro.auth;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/3/15 0015 下午 6:49
 */
public class AuthFilter extends AuthenticatingFilter {

    private static final String TOKEN_KEY = "token";

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String token = httpRequest.getHeader(TOKEN_KEY);
        if (StringUtils.isEmpty(token)) {
            token = httpRequest.getParameter(TOKEN_KEY);
            if (StringUtils.isEmpty(token)) {
                return null;
            }
        }
        return new AuthToken(token);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        return false;
    }
}
