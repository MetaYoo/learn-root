package com.kotall.learn.jpa.dal.many2many;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * https://blog.csdn.net/zyqblog/article/details/80725771
 *
 * @author wzp
 */
@Getter
@Setter
@Entity
@Table(name = "t_student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "t_teacher_student",
            joinColumns = {@JoinColumn(name = "ref_stu_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "ref_tea_id", referencedColumnName = "id")})
    private Set<Teacher> teachers = new HashSet<>();

}
