package com.kotall.learn.springboot.service;

import com.kotall.learn.springboot.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @author : zpwang
 * @version : 1.0.0
 * @date : 2018/3/10
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(UserEntity userEntity) {
         jdbcTemplate.update("insert into t_user(username, age) values(?, ?)", userEntity.getUsername(), userEntity.getAge());
    }
}
