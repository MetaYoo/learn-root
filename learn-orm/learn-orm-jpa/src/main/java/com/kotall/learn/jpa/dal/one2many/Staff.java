package com.kotall.learn.jpa.dal.one2many;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * desc:
 *
 * @author zpwang
 * @date 2020/3/13 11:47
 * @since 1.0.0
 */
@Getter
@Setter
@Entity
@Table(name = "t_staff")
public class Staff {
    @Id
    @Column(name = "staff_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long staffId;
    @Column(name = "staff_name")
    private String staffName;

    /**
     * (targetEntity = Dept.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
     */
    @ManyToOne
    @JoinColumn(name = "ref_dept_id", referencedColumnName = "dept_id")
    private Dept dept;
}
