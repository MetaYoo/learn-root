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

import com.kotall.learn.shardingsphere.entity.Order;
import com.kotall.learn.shardingsphere.mapper.OrderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author MetaYoo
 */
@RunWith(value = SpringRunner.class)
@SpringBootTest
public class ShardingJdbcBootTest {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void test_addOrder() {
        Order order = new Order();
        order.setOrderDesc("测试订单");
        order.setUserId(1L);
        orderMapper.insert(order);
    }
}
