package com.kotall.learn.dynamicds.service;

import com.kotall.learn.dynamicds.ds.DatabaseContextHolder;
import com.kotall.learn.dynamicds.ds.DatabaseType;
import com.kotall.learn.dynamicds.entity.User;
import com.kotall.learn.dynamicds.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * desc:
 *
 * @author zpwang
 * @date 2019/3/29 12:23
 * @since 1.0.0
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getById(int id) {
        DatabaseContextHolder.setDatabaseType(DatabaseType.SLAVE);
        return this.userMapper.getById(id);
    }
}
