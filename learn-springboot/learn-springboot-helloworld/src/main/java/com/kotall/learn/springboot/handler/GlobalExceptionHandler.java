package com.kotall.learn.springboot.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : zpwang
 * @version : 1.0.0
 * @date : 2018/3/10
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    private String defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception {
        log.warn("===================Handle Controller Exception===========" + e.getLocalizedMessage());
        return "{'error': true}";
    }

}
