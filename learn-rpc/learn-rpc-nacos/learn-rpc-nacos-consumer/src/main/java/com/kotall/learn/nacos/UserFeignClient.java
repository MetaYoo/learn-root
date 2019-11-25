package com.kotall.learn.nacos;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * desc:
 *
 * @author zpwang
 * @date 2019/11/25 11:22
 * @since 1.0.0
 */
@FeignClient(name = "nacos-provider")
public interface UserFeignClient {

    /**
     * login
     *
     * @param userName
     * @param passWord
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    String login(@RequestParam("userName") String userName, @RequestParam("passWord") String passWord);
}
