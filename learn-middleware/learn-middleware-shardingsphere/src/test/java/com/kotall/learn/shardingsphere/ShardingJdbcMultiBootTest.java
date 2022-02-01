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
import com.kotall.learn.shardingsphere.entity.Order;
import com.kotall.learn.shardingsphere.mapper.OrderMapper;
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
public class ShardingJdbcMultiBootTest {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void test_addOrder() {
        for (int i = 0; i < 10; i++) {
            for (int j = 1001; i < 1100; i++) {
                Order order = new Order();
                order.setOrderDesc("多数据源-测试订单-" + j);
                order.setUserId(Long.valueOf(i));
                orderMapper.insert(order);
            }
        }
    }

    @Test
    public void test_getOrder() {
        // 会多数据源轮询
        //Order order = orderMapper.selectById(1488365551926427650L);
        // 精确查询
        Order order = orderMapper.selectOne(new LambdaQueryWrapper<Order>().eq(Order::getOrderId, 1488365551926427650L).eq(Order::getUserId, 433L));
        System.out.println(order);
    }

    @Test
    public void test_updateOrder() {
        Order order = orderMapper.selectById(1488365551926427650L);
        order.setOrderDesc("测试订单-更新");
        orderMapper.updateById(order);
    }

    @Test
    public void test_deleteOrder() {
        // 多个数据源轮询删除
        Wrapper<Order> wrapper = new LambdaQueryWrapper<Order>().eq(Order::getOrderId, 1488365551926427650L);
        int count = orderMapper.delete(wrapper);
        System.out.println(count);
    }

    @Test
    public void test_queryOrder() {
        // 多数据源多表轮询查询
        Wrapper<Order> wrapper = new LambdaQueryWrapper<Order>();
        List<Order> orders = orderMapper.selectList(wrapper);
        System.out.println(orders.size());
    }

    @Test
    public void test_queryOrderByPage() {
        Wrapper<Order> wrapper = new LambdaQueryWrapper<Order>();
        Page<Order> page = new Page<>();
        page.setCurrent(1L);
        page.setSize(5L);
        Page<Order> orderPage = orderMapper.selectPage(page, wrapper);
        System.out.println(orderPage.getTotal());
        System.out.println(orderPage.getPages());
        System.out.println(orderPage.getRecords());
    }
}
