package com.kotall.learn.mapper;

import com.kotall.learn.spring.bean.Blog;

/**
 * @author zpwang
 * @version 1.0.0
 */
public interface BlogMapper {

    Blog getById(Integer id);
}
