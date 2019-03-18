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
 * @version $Id: PaymentAction.java, v 0.1 Dec 29, 2016 11:33:11 AM phoenix Exp $
 */
public class PaymentAction implements IAction {

    /** 
     * @see com.kotall.core.engine.IAction#process(BusinessContext)
     */

    public void exec(BusinessContext context) throws Exception {
        //        System.out.println("PaymentAction");
        //        System.out.println(context.getBaseActionContext().getPayOrgId());

        context.getPaymentOrgModel().getPayOrgId();

    }

}
