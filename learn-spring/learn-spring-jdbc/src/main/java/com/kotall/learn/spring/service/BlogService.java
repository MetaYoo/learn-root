package com.kotall.learn.spring.service;

import com.kotall.learn.spring.entity.Blog;

/**
 * @author zpwang
 * @version 1.0.0
 */
public interface BlogService {

    void post(String content);

    Blog getById(Integer id);
}
