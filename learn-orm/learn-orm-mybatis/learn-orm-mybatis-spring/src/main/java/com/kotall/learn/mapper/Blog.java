package com.kotall.learn.mapper;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Blog {
    private int id;
    private String title;
    private String content;
}
