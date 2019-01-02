package com.kotall.learn;


import com.kotall.learn.mapper.Blog;
import com.kotall.learn.mapper.BlogMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
            Blog blog = blogMapper.selectBlog(1);
            System.out.println(blog);

            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
