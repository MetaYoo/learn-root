package com.kotall.learn.nacos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * desc:
 *
 * @author zpwang
 * @date 2019/11/25 11:23
 * @since 1.0.0
 */
@RestController
public class UserController {

    @Autowired
    private UserFeignClient userFeignClient;

    /**
     * 用户账号名和密码登录
     *
     * @param userName
     * @param passWord
     * @return
     */
    @RequestMapping("/login")
    public String login(@RequestParam("userName") String userName, @RequestParam("passWord") String passWord) {
        return userFeignClient.login(userName, passWord);
    }
}
