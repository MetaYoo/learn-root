package com.kotall.learn.rbac.common.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * 描述：
 *
 * @author: zpwang
 * @time: 2019/5/19 18:23
 */
@Entity
@Data
public class SysUser {

    @Id
    private Integer id;
    private String username;
    private String password;
    private Boolean phone;
    private Integer createdBy;
    private Date createTime;
    private Date updateTime;
}
