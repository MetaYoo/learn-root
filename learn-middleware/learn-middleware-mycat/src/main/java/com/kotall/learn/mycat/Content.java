package com.kotall.learn.mycat;

import lombok.Data;

import javax.persistence.*;

/**
 * 描述：
 *
 * @author: zpwang
 * @time: 2019/3/28 22:26
 */
@Data
@Entity
@Table(name = "content")
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String txId;
    private String title;
    private String content;
}
