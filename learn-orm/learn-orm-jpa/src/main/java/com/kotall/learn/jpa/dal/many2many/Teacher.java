package com.kotall.learn.jpa.dal.many2many;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "t_teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "teachers", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Student> students = new HashSet<>();
}
