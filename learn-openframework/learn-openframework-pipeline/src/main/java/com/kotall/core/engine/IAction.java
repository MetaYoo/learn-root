/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.kotall.core.engine;

import com.kotall.core.context.BusinessContext;

/**
 * 
 * @author wenzhi.wang
 * @version $Id: IAction.java, v 0.1 Dec 31, 2016 11:12:05 AM phoenix Exp $
 */
public interface IAction {

    /**
     * 具体业务进行实现
     * @param context
     */
    public void exec(BusinessContext context) throws Exception;

    /**
     * 获取该Action的名称
     */
    //    public void getDesc();
}
