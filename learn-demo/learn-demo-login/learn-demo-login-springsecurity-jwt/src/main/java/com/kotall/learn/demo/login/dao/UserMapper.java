package com.kotall.learn.demo.login.dao;

import com.kotall.learn.demo.login.entity.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @author zpwang
 * @version 1.0.0
 */
@Mapper
@Component
public interface UserMapper {

    @Insert("insert into user(username, password, nickname, roles) values(#{username}, #{password}, #{nickname}, #{roles})")
    int insert(UserEntity userEntity);

    @Select("select * from user where username = #{username}")
    UserEntity selectByUsername(@Param("username") String username);
}
