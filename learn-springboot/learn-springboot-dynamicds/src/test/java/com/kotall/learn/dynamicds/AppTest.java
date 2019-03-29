package com.kotall.learn.dynamicds;

import com.kotall.learn.dynamicds.entity.User;
import com.kotall.learn.dynamicds.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {
    @Resource
    private UserService userService;
    @Test
    public void shouldAnswerWithTrue() {
        User user = userService.getById(1);
    }
}
