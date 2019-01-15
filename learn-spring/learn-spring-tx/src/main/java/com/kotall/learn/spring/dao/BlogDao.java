package com.kotall.learn.spring.dao;

/**
 * @author zpwang
 * @version 1.0.0
 */
public interface BlogDao {
    int saveOrUpdate(String blogContent);
}
