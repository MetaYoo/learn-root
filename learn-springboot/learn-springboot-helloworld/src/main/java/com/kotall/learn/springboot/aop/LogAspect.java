package com.kotall.learn.springboot.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * desc:
 *
 * @author zpwang
 * @date 2019/9/29 13:22
 * @since 1.0.0
 */
@Aspect
@Component
public class LogAspect {
    @Pointcut("execution(public * com.kotall.learn.springboot.api.HelloWorldController.hello(..))")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        try {
            long startTime = System.currentTimeMillis();
            Object result = joinPoint.proceed();
            long endTime = System.currentTimeMillis();
            String className = joinPoint.getTarget().getClass().getName();
            System.out.println("=====>" +className);
            return result;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }
}
