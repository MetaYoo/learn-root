package com.kotall.learn.spring.service;

import com.kotall.learn.spring.dao.BlogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zpwang
 * @version 1.0.0
 */
@Service("blogService")
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Override
    public void post(String content) {
        int rt = this.blogDao.saveOrUpdate(content);
    }
}
