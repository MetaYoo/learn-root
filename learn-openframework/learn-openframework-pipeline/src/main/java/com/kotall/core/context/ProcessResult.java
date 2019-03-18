/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.kotall.core.context;

/**
 * 
 * @author wenzhi.wang
 * @version $Id: ProcessResult.java, v 0.1 Dec 31, 2016 11:15:36 AM phoenix Exp $
 */
public class ProcessResult {
    /** 是否成功 */
    private boolean         success;
    /** 结果砄1�7 */
    private ResultCode      resultCode;

    /** 业务上下斄1�7 */
    private BusinessContext context;

    /**
     * 构�1�7�方法�1�7�1�7
     * 
     * @param resultCode
     *            结果砄1�7
     */
    public ProcessResult(ResultCode resultCode) {
        success = resultCode == ResultCode.SUCCESS;
        this.resultCode = resultCode;
    }

    /**
     * 构�1�7�方法�1�7�1�7
     * 
     * @param resultCode
     *            结果砄1�7
     * @param context
     *            上下斄1�7
     */
    public ProcessResult(ResultCode resultCode, BusinessContext context) {
        success = resultCode == ResultCode.SUCCESS;
        this.resultCode = resultCode;
        this.context = context;
    }

    /**
     * Getter method for property <tt>success</tt>.
     * 
     * @return property value of success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Setter method for property <tt>success</tt>.
     * 
     * @param success
     *            value to be assigned to property success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * Getter method for property <tt>resultCode</tt>.
     * 
     * @return property value of resultCode
     */
    public ResultCode getResultCode() {
        return resultCode;
    }

    /**
     * Setter method for property <tt>resultCode</tt>.
     * 
     * @param resultCode
     *            value to be assigned to property resultCode
     */
    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * Getter method for property <tt>context</tt>.
     * 
     * @return property value of context
     */
    public BusinessContext getContext() {
        return context;
    }

    /**
     * Setter method for property <tt>context</tt>.
     * 
     * @param context
     *            value to be assigned to property context
     */
    public void setContext(BusinessContext context) {
        this.context = context;
    }

    /** 
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return null;
        //        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
