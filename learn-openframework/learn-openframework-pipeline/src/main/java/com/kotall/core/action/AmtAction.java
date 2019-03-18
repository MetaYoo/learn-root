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
public class AmtAction implements IAction {

    /** 
     * @see com.kotall.core.engine.IAction#process(BusinessContext)
     */

    public void exec(BusinessContext context) {
        //        System.out.println("AmtAction");
        context.getPaymentOrgModel().getPaymentAmt();
        //        System.out.println(context.getBaseActionContext().getPaymentAmt());
    }

}
