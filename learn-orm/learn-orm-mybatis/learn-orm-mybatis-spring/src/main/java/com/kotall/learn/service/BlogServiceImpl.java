package com.kotall.learn.service;

import com.kotall.learn.mapper.Blog;
import com.kotall.learn.mapper.BlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zpwang
 * @version 1.0.0
 */
@Service("blogService")
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public Blog getById(Integer id) {
        return this.blogMapper.selectBlog(id);
    }
}
