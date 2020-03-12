package com.kotall.learn.jpa.dal.one2many;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "t_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "order_desc")
    private String orderDesc;
    @OneToMany
    private Set<OrderItem> orderItems;
}
