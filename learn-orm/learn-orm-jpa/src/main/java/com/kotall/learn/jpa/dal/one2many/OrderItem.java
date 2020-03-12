package com.kotall.learn.jpa.dal.one2many;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "t_order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "order_id")
    private Integer orderId;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private Integer price;


}
