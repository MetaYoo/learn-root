package com.kotall.learn.springboot.api;

import com.kotall.learn.springboot.dto.UserDTO;
import org.springframework.web.bind.annotation.*;

/**
 * @author zpwang
 * @version 1.0.0
 */
@RestController
public class HelloController {

    @GetMapping("/{param}")
    public String hello(@PathVariable("param") String param) {
        return "您好 网站暂时还未上线，敬请期待！";
    }

    @GetMapping("/demo")
    public String getDemo(UserDTO userDTO) {
        System.out.println(userDTO);
        return userDTO.toString();
    }

    @PostMapping("/demo")
    public String postDemo(@RequestBody UserDTO userDTO) {
        System.out.println(userDTO);
        return userDTO.toString();
    }
}
