package com.kotall.learn.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class MybatisTest1 {

    @Test
    public void test1() {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            Blog blog = sqlSession.selectOne("com.kotall.learn.mybatis.BlogMapper.selectBlog", 1);
            Assert.assertNotNull(blog);

            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
            Blog blog = blogMapper.selectBlog(1);
            Assert.assertNotNull(blog);

            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
