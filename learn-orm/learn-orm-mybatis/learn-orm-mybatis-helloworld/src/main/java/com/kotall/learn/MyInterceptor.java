package com.kotall.learn;

import org.apache.ibatis.executor.BaseExecutor;
import org.apache.ibatis.executor.CachingExecutor;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

/**
 * desc:
 * mybatis 拦截器
 *
 * @author zpwang
 * @date 2020/1/14 14:16
 * @since 1.0.0
 */
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class MyInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("拦截到了mybatis的BaseExecutor中的方法");
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof BaseExecutor || target instanceof CachingExecutor) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
