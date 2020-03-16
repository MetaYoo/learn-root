package com.kotall.learn.jpa;

import com.kotall.learn.jpa.dal.querydsl.Blog;
import com.kotall.learn.jpa.dal.querydsl.JpaBlogRepository;
import com.kotall.learn.jpa.dal.querydsl.QBlog;
import com.querydsl.core.types.Predicate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaQueryDslTest {

    @Autowired
    JpaBlogRepository jpaBlogRepository;


    @Test
    public void query() {
        Blog blog = jpaBlogRepository.getOne(1);
        System.out.println(blog);
    }

    @Test
    public void queryWithDsl() {
        QBlog qBlog = QBlog.blog;
        Predicate predicate = qBlog.title.eq("hello");
        Iterable<Blog> it = jpaBlogRepository.findAll(predicate);
        System.out.println("");
    }


}
