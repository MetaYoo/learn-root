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
 * @version $Id: ResulAction.java, v 0.1 Dec 29, 2016 2:52:03 PM phoenix Exp $
 */
public class ResulAction implements IAction {

    /** 
     * @see com.kotall.core.engine.IAction#process(BusinessContext)
     */

    public void exec(BusinessContext context) throws Exception {
        Thread.sleep(100);
        //        System.out.println("ResulAction");
        //        System.out.println(context.getMap("abc"));
        //        System.out.println(context.getBaseActionContext().getTradeNo());
    }

}
