package com.kotall.learn.service;

import com.kotall.learn.mybatis.Blog;

/**
 * @author zpwang
 * @version 1.0.0
 */
public interface BlogService {

    Blog getById(Integer id);
}
