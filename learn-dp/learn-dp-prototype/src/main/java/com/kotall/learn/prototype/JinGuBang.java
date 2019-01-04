package com.kotall.learn.prototype;

import lombok.Data;

import java.io.Serializable;

/**
 * 金箍棒
 * @author zpwang
 * @version 1.0.0
 */
@Data
public class JinGuBang implements Serializable {

    private static final long serialVersionUID = -6986076486859349501L;

    private int length;
    private String color;
}
