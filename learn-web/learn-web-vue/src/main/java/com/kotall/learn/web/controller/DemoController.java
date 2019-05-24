package com.kotall.learn.web.controller;

import com.google.common.collect.Lists;
import com.kotall.learn.web.common.Page;
import com.kotall.learn.web.dto.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * desc:
 *
 * @author zpwang
 * @date 2019/5/24 19:50
 * @since 1.0.0
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/users")
    public Page users() {
        List<User> users = Lists.newArrayList();
        for (int i=0; i < 1000; i++) {
            User user = new User();
            user.setId(i);
            users.add(user);
        }
        Page page = new Page();
        page.setCode(0);
        page.setCount(1000);
        page.setLimit(25);
        page.setData(users);
        return page;
    }

}
