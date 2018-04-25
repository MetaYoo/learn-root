package com.kotall.learn.io.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.ToString;

/**
 * @author : zpwang
 * @version : 1.0.0
 * @date : 2018/4/25
 */
@Data
@ToString
@XStreamAlias("Address")
public class Address {

    @XStreamAlias("Id")
    private int id;

    @XStreamAlias("City")
    private String city;

}
