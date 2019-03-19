package com.kotall.learn.jms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * desc:
 *
 * @author zpwang
 * @create 2019/3/19 19:01
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaDemoTest {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Test
    public void testSendMsg() throws Exception {
        this.kafkaTemplate.send("test", "k1", "v1");
        System.out.println("msg send successfully!");
        TimeUnit.SECONDS.sleep(5);
    }
}
