/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.kotall.core.context;

import java.math.BigDecimal;

/**
 * 支付机构模型
 * @author wenzhi.wang
 * @version $Id: PaymentOrgModel.java, v 0.1 Dec 31, 2016 5:31:25 PM phoenix Exp $
 */
public class PaymentOrgModel {
    /** tradeNo */

    private String     tradeNo;

    /** 支付金额 */

    private BigDecimal paymentAmt;

    /** ֧支付机构id */

    private String     payOrgId;

    /**
     * Getter method for property <tt>tradeNo</tt>.
     * 
     * @return property value of tradeNo
     */
    public String getTradeNo() {
        return tradeNo;
    }

    /**
     * Setter method for property <tt>tradeNo</tt>.
     * 
     * @param tradeNo value to be assigned to property tradeNo
     */
    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    /**
     * Getter method for property <tt>paymentAmt</tt>.
     * 
     * @return property value of paymentAmt
     */
    public BigDecimal getPaymentAmt() {
        return paymentAmt;
    }

    /**
     * Setter method for property <tt>paymentAmt</tt>.
     * 
     * @param paymentAmt value to be assigned to property paymentAmt
     */
    public void setPaymentAmt(BigDecimal paymentAmt) {
        this.paymentAmt = paymentAmt;
    }

    /**
     * Getter method for property <tt>payOrgId</tt>.
     * 
     * @return property value of payOrgId
     */
    public String getPayOrgId() {
        return payOrgId;
    }

    /**
     * Setter method for property <tt>payOrgId</tt>.
     * 
     * @param payOrgId value to be assigned to property payOrgId
     */
    public void setPayOrgId(String payOrgId) {
        this.payOrgId = payOrgId;
    }

}
