/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.epcc.finexchange;

import java.math.BigDecimal;

import com.kotall.core.context.BusinessContext;
import com.kotall.core.context.PaymentOrgModel;
import com.kotall.core.engine.ProcessEngineImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * 
 * @author wenzhi.wang
 * @version $Id: Test.java, v 0.1 Dec 29, 2016 11:35:29 AM phoenix Exp $
 */
public class ProcessEngineTest {

    /**
     * 
     * @param args
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException {
        ApplicationContext applicationcontext = new ClassPathXmlApplicationContext(
            "core-service.xml");
        testEngine(applicationcontext);
    }

    public static void testEngine(ApplicationContext applicationcontext) {
        //        ApplicationContext applicationcontext = new ClassPathXmlApplicationContext(
        //            "core-service.xml");
        BusinessContext context = new BusinessContext();
        PaymentOrgModel paymentOrgModel = new PaymentOrgModel();

        paymentOrgModel.setPaymentAmt(new BigDecimal("100"));
        paymentOrgModel.setPayOrgId("支付宝");
        paymentOrgModel.setTradeNo("第一笔交易");
        context.setBizType("abc");
        context.setPaymentOrgModel(paymentOrgModel);

        ProcessEngineImpl engine = (ProcessEngineImpl) applicationcontext.getBean("processEngine");

        engine.process(context);

    }

}
