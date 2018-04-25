package com.kotall.learn.io.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author : zpwang
 * @version : 1.0.0
 * @date : 2018/4/25
 */
@Data
@ToString
@XStreamAlias("Person")
public class Person {

    @XStreamAlias("Id")
    private int id;

    @XStreamAlias("Name")
    private String name;

    @XStreamAlias("AddressList")
    private List<Address> addressList;

}
