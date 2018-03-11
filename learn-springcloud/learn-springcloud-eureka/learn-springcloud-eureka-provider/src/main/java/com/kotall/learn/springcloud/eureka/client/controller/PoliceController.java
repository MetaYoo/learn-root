package com.kotall.learn.springcloud.eureka.client.controller;

import com.kotall.learn.springcloud.eureka.client.dto.Police;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/3/10 0010 下午 10:24
 */
@RestController
@Slf4j
public class PoliceController {

    @RequestMapping(value = "/call/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Police call(@PathVariable int id, HttpServletRequest request) {
        log.info("===========id=" + id);
        Police police = new Police(id, "arac", request.getRequestURL().toString());
        return police;
    }

}
