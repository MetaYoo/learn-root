package com.kotall.learn.spring.impl;

import com.kotall.learn.spring.BlogService;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class LocalBlogServiceImpl implements BlogService {

    @Override
    public void post(String content) {
        System.out.println("=============== local implementation ===================\r\n" + content);
    }
}
