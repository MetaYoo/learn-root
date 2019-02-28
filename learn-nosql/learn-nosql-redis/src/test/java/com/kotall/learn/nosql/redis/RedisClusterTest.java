package com.kotall.learn.nosql.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.JedisCluster;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisClusterTest {

    @Autowired
    private JedisCluster redisCluster;

    @Test
    public void testRedisTemplate() {
        redisCluster.set("test-key1", "test1");
    }
}
