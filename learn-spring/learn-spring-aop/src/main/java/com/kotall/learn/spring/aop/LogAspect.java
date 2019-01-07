package com.kotall.learn.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class LogAspect {


    public void doBefore(JoinPoint joinpoint) throws Throwable {

        System.out.println("====== 前置切面 =======");

    }

    public void doAfter(JoinPoint joinpoint) throws Throwable {

        System.out.println("====== 后置切面 =======");

    }


    public Object doAround(ProceedingJoinPoint joinpoint) throws Throwable {

        System.out.println("====== 环绕切面开始 =======");
        Object object = joinpoint.proceed();
        System.out.println("====== 环绕切面结束 =======");
        return object;
    }


    public void doAfterReturning(JoinPoint joinpoint, Object ret) {
        System.out.println("====== 方法返回后 =======");
        System.out.println("方法返回对象：" + ret);
    }

    public void doAfterThrowing(JoinPoint joinpoint, Throwable e) throws Exception {
        System.out.println("====== 方法跑出异常后 =======");
        System.out.println("方法跑出的异常：" + e.getClass());
        throw new RuntimeException("异常测试！");
    }



}
