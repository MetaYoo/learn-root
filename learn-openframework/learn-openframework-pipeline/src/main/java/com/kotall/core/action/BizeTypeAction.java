/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.kotall.core.action;

import com.kotall.core.engine.IAction;
import com.kotall.core.context.BusinessContext;

/**
 * 
 * @author wenzhi.wang
 * @version $Id: AmtAction.java, v 0.1 Dec 29, 2016 11:34:06 AM phoenix Exp $
 */
public class BizeTypeAction implements IAction {

    /** 
     * @see com.kotall.core.engine.IAction#process(BusinessContext)
     */

    public void exec(BusinessContext context) throws Exception {
        //        System.out.println("BizeTypeAction");
        context.getPaymentOrgModel().setTradeNo("第一笔交易");
        context.put("abc", "测试信息1");
        context.put("ddd", new byte[1024 * 1024 * 10]);
        //        System.out.println(;);
    }

}
