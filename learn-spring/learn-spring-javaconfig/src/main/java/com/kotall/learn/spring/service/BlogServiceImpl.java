package com.kotall.learn.spring.service;

import com.kotall.learn.spring.bean.Blog;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class BlogServiceImpl implements BlogService {

    @Override
    public Blog getById(Integer id) {
        Blog blog = new Blog();
        blog.setId(1);
        blog.setName("test");
        return blog;
    }
}
