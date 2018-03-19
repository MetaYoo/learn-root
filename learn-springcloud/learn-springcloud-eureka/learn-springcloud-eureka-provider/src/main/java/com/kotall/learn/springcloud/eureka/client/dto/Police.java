package com.kotall.learn.springcloud.eureka.client.dto;

import lombok.Data;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/3/10 0010 下午 10:22
 */
@Data
public class Police {
    private int id;
    private String name;
    private String msg;

    public Police() {}

    public Police(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Police(int id, String name, String msg) {
        this.id = id;
        this.name = name;
        this.msg = msg;
    }

}