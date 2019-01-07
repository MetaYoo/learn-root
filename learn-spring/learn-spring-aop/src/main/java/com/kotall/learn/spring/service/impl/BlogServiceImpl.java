package com.kotall.learn.spring.service.impl;

import com.kotall.learn.spring.service.BlogService;
import org.springframework.stereotype.Service;

/**
 * @author zpwang
 * @version 1.0.0
 */
@Service("blogService")
public class BlogServiceImpl implements BlogService {
    @Override
    public void post(String content) {
        System.out.println("发表文章内容如下：" + content);
    }
}
