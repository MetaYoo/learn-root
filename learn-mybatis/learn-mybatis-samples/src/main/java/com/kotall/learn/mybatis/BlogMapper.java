package com.kotall.learn.mybatis;

import java.util.List;

public interface BlogMapper {

    List<Blog> selectBlog(Integer id);
}
