package com.kotall.learn.jpa.dal.querydsl;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * desc:
 *
 * @author zpwang
 * @date 2020/3/13 16:59
 * @since 1.0.0
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "t_blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private String content;
    private Date date;
}
