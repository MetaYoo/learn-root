package com.kotall.learn.mycat;

import lombok.Data;

import javax.persistence.*;

/**
 * 描述：
 *
 * @author: zpwang
 * @time: 2019/3/28 22:26
 */

@Table(name = "content")
@Data
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "txid")
    private String txId;

    private String title;

    private String content;
}
