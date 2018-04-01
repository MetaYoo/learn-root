package com.kotall.learn.shiro.auth;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        return new AccessToken(token);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String token = httpRequest.getHeader(TOKEN_KEY);
        if(StringUtils.isEmpty(token)){
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setContentType("application/json;charset=utf-8");
            String result = "{" +
                    "\"code\": \"" + HttpStatus.UNAUTHORIZED + "\"," +
                    "\"msg\": \"invalid token\"" +
                    "}";
            httpResponse.getWriter().print(result);
            return false;
        }
        return executeLogin(request, response);
    }
}
