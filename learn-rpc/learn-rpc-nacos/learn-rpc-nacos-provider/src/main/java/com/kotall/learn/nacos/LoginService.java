package com.kotall.learn.nacos;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * desc:
 *
 * @author zpwang
 * @date 2019/11/25 11:10
 * @since 1.0.0
 */
@RestController
public class LoginService {

    @RequestMapping("/login")
    public String login(@RequestParam("userName") String userName, @RequestParam("passWord") String passWord) {
        System.out.println("============================================");
        if (userName.equals("leon") && passWord.equals("888")) {
            return "nacos-provider: login success";
        }
        return "nacos-provider: login fail";
    }
}
