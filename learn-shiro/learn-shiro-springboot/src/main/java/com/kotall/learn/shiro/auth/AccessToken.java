package com.kotall.learn.shiro.auth;

import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/3/25 0025 上午 9:34
 */
@Data
public class AccessToken implements AuthenticationToken {

    private String token;

    public AccessToken(){}

    public AccessToken(String token){
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return this;
    }

    @Override
    public Object getCredentials() {
        return this;
    }
}
