package com.kotall.learn.rbac.common.entity;

import lombok.Data;

import java.util.Date;

/**
 * 描述：
 *
 * @author: zpwang
 * @time: 2019/5/19 18:23
 */
@Data
public class SysUser {
    private Integer userId;
    private String username;
    private String password;
    private Boolean isActive;
    private Integer createdBy;
    private Date createTime;
    private Date updateTime;
}
