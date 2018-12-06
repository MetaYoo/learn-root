package com.kotall.learn.dubbo.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/1/24 0024 下午 9:52
 */
@Getter
@Setter
@ToString
public class BaseDto implements Serializable {

    private int sysId;
}
