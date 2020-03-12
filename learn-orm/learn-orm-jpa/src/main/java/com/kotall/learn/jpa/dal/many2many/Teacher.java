package com.kotall.learn.jpa.dal.many2many;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "t_teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "name")
    private String name;
}
