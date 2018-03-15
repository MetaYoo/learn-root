package com.kotall.learn.shiro.auth;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/3/15 0015 下午 6:41
 */
public class AuthToken implements AuthenticationToken {

    private static final long serialVersionUID = 1L;

    private String token;

    public AuthToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }
}
