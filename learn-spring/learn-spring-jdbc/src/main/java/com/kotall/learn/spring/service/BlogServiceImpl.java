package com.kotall.learn.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author zpwang
 * @version 1.0.0
 */
@Service("blogService")
public class BlogServiceImpl implements BlogService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void post(String content) {
        int rt = jdbcTemplate.update("insert into t_blog(title, content) values (?, ?)", "标题", content);
        Assert.isTrue( rt > 0);
    }
}
