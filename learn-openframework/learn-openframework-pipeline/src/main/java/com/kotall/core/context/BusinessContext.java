/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.kotall.core.context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @author wenzhi.wang
 * @version $Id: BusinessContext.java, v 0.1 Dec 31, 2016 11:15:12 AM phoenix Exp $
 */
public class BusinessContext {

    /**  业务类型*/
    private String              bizType;

    /** 扩展信息 */
    private Map<String, Object> extendContent = new ConcurrentHashMap<String, Object>();

    private PaymentOrgModel     paymentOrgModel;

    /**
     * Getter method for property <tt>paymentOrgModel</tt>.
     * 
     * @return property value of paymentOrgModel
     */
    public PaymentOrgModel getPaymentOrgModel() {
        return paymentOrgModel;
    }

    /**
     * Setter method for property <tt>paymentOrgModel</tt>.
     * 
     * @param paymentOrgModel value to be assigned to property paymentOrgModel
     */
    public void setPaymentOrgModel(PaymentOrgModel paymentOrgModel) {
        this.paymentOrgModel = paymentOrgModel;
    }

    /**
     * Getter method for property <tt>bizType</tt>.
     * 
     * @return property value of bizType
     */
    public String getBizType() {
        return bizType;
    }

    /**
     * Setter method for property <tt>bizType</tt>.
     * 
     * @param bizType value to be assigned to property bizType
     */
    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    /**
     * Getter method for property <tt>extendContent</tt>.
     * 
     * @return property value of extendContent
     */
    public Object get(String key) {
        return extendContent.get(key);
    }

    /**
     * Setter method for property <tt>extendContent</tt>.
     * 
     * @param extendContent value to be assigned to property extendContent
     */
    public void put(String key, Object value) {
        extendContent.put(key, value);
    }

}
