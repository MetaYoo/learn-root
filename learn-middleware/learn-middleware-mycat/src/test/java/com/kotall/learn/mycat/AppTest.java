package com.kotall.learn.mycat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {

    @Autowired
    private ContentJpaRepository contentDao;

    @Test
    public void shouldAnswerWithTrue() {
        Content content = new Content();
        content.setTxId("20190328010101");
        content.setTitle("test");
        content.setContent("test content");
        contentDao.save(content);
        System.out.println(content.getId());
    }
}
