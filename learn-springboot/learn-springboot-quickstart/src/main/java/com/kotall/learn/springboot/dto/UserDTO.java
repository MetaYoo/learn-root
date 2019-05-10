package com.kotall.learn.springboot.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * desc:
 *
 * @author zpwang
 * @date 2019/5/10 18:58
 * @since 1.0.0
 */
@Data
public class UserDTO implements Serializable {
    @JSONField(name = "user_name")
    private String username;
    private String age;
}
