package com.kotall.learn.web.dto;

import lombok.Data;

/**
 * desc:
 *
 * @author zpwang
 * @date 2019/5/24 19:51
 * @since 1.0.0
 */
@Data
public class User {

    private int id;
    private String username;
    private String sex;
    private String city;
    private String sign;
    private int experience;
    private int score;
    private String classify;
    private int wealth;
}
