package com.kotall.learn.jpa.dal.one2many;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * desc:
 *
 * @author zpwang
 * @date 2020/3/13 11:45
 * @since 1.0.0
 */
@Getter
@Setter
@Entity
@Table(name = "t_dept")
public class Dept {
    @Id
    @Column(name = "dept_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deptId;
    @Column(name = "dept_name")
    private String deptName;

    @OneToMany(mappedBy = "dept", cascade = CascadeType.ALL)
    private Set<Staff> staffs = new HashSet<>();
}
