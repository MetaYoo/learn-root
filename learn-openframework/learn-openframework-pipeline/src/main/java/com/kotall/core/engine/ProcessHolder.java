/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.kotall.core.engine;

import com.kotall.core.context.BusinessContext;

/**
 * 处理器调用上下文Holder
 * @author wenzhi.wang
 * @version $Id: ProcessHolder.java, v 0.1 Dec 31, 2016 11:23:22 AM phoenix Exp $
 */
public class ProcessHolder {
    /**
     * 保存本处理线程中的调用上下文信息 - Action
     */
    private final static ThreadLocal<BusinessContext> processLocal = new ThreadLocal<BusinessContext>();

    /**
     * 私有构造函数
     */
    private ProcessHolder() {
    }

    /**
     * 创建ProcessRuntimeContext
     * 
     * @param type
     * @return
     */
    public static void createProcessContext(BusinessContext context) {
        clear();
        processLocal.set(context);

    }

    /**
     * 获取调用上下文 - Action
     *
     * @return
     */
    public static BusinessContext getProcessContext() {
        return processLocal.get();
    }

    /**
     * 清除threadLocal - Action
     */
    public static void clear() {
        processLocal.set(null);
        processLocal.remove();
        //        
    }
}
