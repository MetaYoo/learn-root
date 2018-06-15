package com.kotall.learn.dubbo.client.filter;

import com.alibaba.dubbo.rpc.*;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/1/24 0024 下午 9:01
 */
public class AuthFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        return invoker.invoke(invocation);
    }
}
