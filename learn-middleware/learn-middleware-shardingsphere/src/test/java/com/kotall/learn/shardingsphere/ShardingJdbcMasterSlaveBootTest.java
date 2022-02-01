package com.kotall.learn.shardingsphere;
/*
 * Copyright 2002-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kotall.learn.shardingsphere.entity.User;
import com.kotall.learn.shardingsphere.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author MetaYoo
 */
@RunWith(value = SpringRunner.class)
@SpringBootTest
public class ShardingJdbcMasterSlaveBootTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test_addUser() {
        for (int i = 1000; i < 2000; i++) {
            User user = new User();
            user.setUserName("测试用户" + i);
//            user.setUserId(1L);
            userMapper.insert(user);
        }
    }

    @Test
    public void test_getUser() {
        User user = userMapper.selectById(1488510936145801220L);
        System.out.println(user);
    }

    @Test
    public void test_updateUser() {
        User user = userMapper.selectById(1488510936145801220L);
        user.setUserName("测试用户-更新");
        userMapper.updateById(user);
    }

    @Test
    public void test_deleteUser() {
        Wrapper<User> wrapper = new LambdaQueryWrapper<User>().eq(User::getUserId, 1488510936145801220L);
        int count = userMapper.delete(wrapper);
        System.out.println(count);
    }

    @Test
    public void test_queryUser() {
        Wrapper<User> wrapper = new LambdaQueryWrapper<User>();
        List<User> Users = userMapper.selectList(wrapper);
        System.out.println(Users.size());
    }

    @Test
    public void test_queryUserByPage() {
        Wrapper<User> wrapper = new LambdaQueryWrapper<User>();
        Page<User> page = new Page<>();
        page.setCurrent(1L);
        page.setSize(5L);
        Page<User> userPage = userMapper.selectPage(page, wrapper);
        System.out.println(userPage.getTotal());
        System.out.println(userPage.getPages());
        System.out.println(userPage.getRecords());
    }
}
