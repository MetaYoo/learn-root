package com.kotall.learn.jpa.dal.one2many;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * desc:
 *
 * @author zpwang
 * @date 2020/3/13 14:04
 * @since 1.0.0
 */
public interface DeptRepository extends JpaRepository<Dept, Long> {
}
