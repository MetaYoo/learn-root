package com.kotall.learn.shiro.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/3/25 0025 上午 9:34
 */
@Data
@Entity
public class AccessToken {

    @Id
    private Integer id;

    private String token;

    public AccessToken(){}

    public AccessToken(String token){
        this.token = token;
    }


}
