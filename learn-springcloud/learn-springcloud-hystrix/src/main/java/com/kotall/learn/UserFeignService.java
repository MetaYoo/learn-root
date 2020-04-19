package com.kotall.learn;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value= "user-service", fallback = UserFeignFailBackImpl.class)
public interface UserFeignService {

    @RequestMapping(value = "/provider/getUser")
    public String getUser(@RequestParam("id") Integer id);
}
