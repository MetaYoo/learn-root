package com.kotall.learn.nosql;

import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 描述：
 *
 * @author: zpwang
 * @time: 2019/5/8 21:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EsQueryTest {
    @Resource
    private RestHighLevelClient client;

    @Test
    public void testQuery1() {

    }
}
