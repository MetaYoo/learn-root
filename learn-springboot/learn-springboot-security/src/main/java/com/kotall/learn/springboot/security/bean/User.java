package com.kotall.learn.springboot.security.bean;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author zpwang
 * @version 1.0.0
 */
@Data
@ToString
@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue
    private long id;

    private String username;

    private String password;

}
