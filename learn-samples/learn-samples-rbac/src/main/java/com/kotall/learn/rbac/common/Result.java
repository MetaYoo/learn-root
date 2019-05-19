package com.kotall.learn.rbac.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 描述：
 *
 * @author: zpwang
 * @time: 2019/5/19 15:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result implements Serializable {
    private int code;
    private String msg;


    public static Result ok() {
        return new Result(200, "success");
    }


    public static Result denied() {
        return new Result(401, "access denied");
    }

    public static Result error() {
        return new Result(500, "server error");
    }


}
