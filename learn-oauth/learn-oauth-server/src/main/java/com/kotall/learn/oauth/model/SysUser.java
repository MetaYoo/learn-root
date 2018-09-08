package com.kotall.learn.oauth.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sys_user")
@Data
public class SysUser {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;

    @Transient
    private List<SysRole> roles;

}
