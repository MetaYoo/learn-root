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
    public void testRedisTemplate() throws Exception {
        for (int i =0; i < 10000; i++) {
            redisCluster.set("test-key" + i, "test" + i);
            Thread.sleep(100);
        }

    }
}
