package com.kotall.learn.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author : zpwang
 * @version : 1.0.0
 * @date : 2018/8/10
 */
@Component
@Aspect
public class LogAop {

    @Pointcut("execution (* com.kotall.learn.spring..*.*(..))")
    public void pointcut(){}

    // 方法执行前调用
    @Before("pointcut()")
    public void before() {
        System.out.println("before");
    }

    // 方法执行前调用
    @After("pointcut()")
    public void after() {
        System.out.println("after");
    }

    // 方法执行的前后调用
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("begin around");
        Object object = null;
        object = point.proceed();
//        try {
//            object = point.proceed();
//        } finally {
//            System.out.println("end around");
//        }


        return object;
    }
}
