package com.kotall.learn.prototype;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 猴子
 * @author zpwang
 * @version 1.0.0
 */
@Data
public class Monkey implements Serializable {

    private static final long serialVersionUID = -8164597713695379711L;

    private int age;
    private String name;
    private Date birthday;
}
