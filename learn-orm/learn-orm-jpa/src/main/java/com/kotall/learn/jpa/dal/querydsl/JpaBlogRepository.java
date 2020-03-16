package com.kotall.learn.jpa.dal.querydsl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * desc:
 *
 * @author zpwang
 * @date 2020/3/13 17:01
 * @since 1.0.0
 */
public interface JpaBlogRepository extends JpaRepository<Blog, Integer>, QuerydslPredicateExecutor<Blog> {
}
