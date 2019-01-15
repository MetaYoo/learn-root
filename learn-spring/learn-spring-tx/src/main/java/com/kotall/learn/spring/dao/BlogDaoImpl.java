package com.kotall.learn.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author zpwang
 * @version 1.0.0
 */
@Repository("blogDao")
public class BlogDaoImpl implements BlogDao {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int saveOrUpdate(String blogContent) {
        this.jdbcTemplate.update("insert into t_blog(title, content) values (?, ?)", "标题", blogContent);
        return this.jdbcTemplate.update("insert into t_blog(title, content) values (?, ?)", "标题-副标题", "副标题");
    }
}
