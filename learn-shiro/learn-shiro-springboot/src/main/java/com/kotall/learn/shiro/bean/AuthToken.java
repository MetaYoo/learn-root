package com.kotall.learn.shiro.bean;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/4/1 0001 上午 11:27
 */
@Data
@Entity
public class AuthToken {
    @Id
    private String id;

    private String token;

    private Date expireTime;

    @UpdateTimestamp
    private Date updateTime;

    public AuthToken() {}

    public AuthToken(String id, String token, Date expireTime) {
        this.id = id;
        this.token = token;
        this.expireTime = expireTime;
    }
}
