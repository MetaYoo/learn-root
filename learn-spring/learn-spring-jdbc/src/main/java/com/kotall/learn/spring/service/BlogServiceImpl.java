package com.kotall.learn.spring.service;

import com.kotall.learn.spring.entity.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;

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
        Assert.isTrue(rt > 0);
    }

    @Override
    public Blog getById(Integer id) {
        String sql = "select t.* from t_blog t where t.id=?";
        return this.jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            Blog blog = new Blog();
            blog.setId(rs.getInt("id"));
            blog.setTitle(rs.getString("title"));
            blog.setContent(rs.getString("content"));
            return blog;
        });
    }
}
