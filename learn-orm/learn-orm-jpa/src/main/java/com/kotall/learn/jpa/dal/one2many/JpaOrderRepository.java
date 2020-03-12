package com.kotall.learn.jpa.dal.one2many;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository extends JpaRepository<Order, Integer> {
}
