/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.kotall.core.context;

import com.kotall.common.util.enums.BaseEnum;

/**
 * 
 * @author wenzhi.wang
 * @version $Id: ResultCode.java, v 0.1 Dec 31, 2016 11:15:58 AM phoenix Exp $
 */
public enum ResultCode implements
                       BaseEnum {

                                 /** ---------------------- 公用类:0×× ---------------------- */

                                 /** 成功 */
                                 SUCCESS("000000000", "成功"),

                                 /** 报文对象为空 */
                                 INSTRUCTION_NULL("100000000", "领域对象为空"),

                                 /** 流程上下文为空 */
                                 PROCESS_CONTEXT_NULL("100000001", "流程上下文为空"),

                                 /** 请求参数非法 */
                                 INVALID_PARAMETER("100000002", "请求参数非法"),

                                 /** 请求方身份为空 */
                                 REQUEST_IDENTIFY_NULL("100000003", "请求方身份为空"),

                                 /**未知*/
                                 UNKNOW("900000000", "未知"),

                                 /**失败*/
                                 FAIL("999999999", "失败"),

    ;

    /** 枚举值  */

    private String code;
    /** 枚举描述 */

    private String message;

    /**
     * 构造方法
     * 
     * @param code
     * @param message
     */
    private ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /** 
     * @see com.kotall.common.util.enums.BaseEnum#getCode()
     */
    public String getCode() {
        return this.code;
    }

    /** 
     * @see com.kotall.common.util.enums.BaseEnum#getMessage()
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * 根据枚举值获取枚举
     * 
     * @param code
     * @return
     */
    public static ResultCode getEnumByCode(String code) {
        for (ResultCode resultCode : values()) {
            if (resultCode.getCode().equals(code)) {
                return resultCode;
            }

        }
        return null;
    }

    /**
     * 获取日志信息
     * 
     * @return
     */
    public String getLogMessage() {
        StringBuffer sb = new StringBuffer();
        sb.append(toString());
        sb.append("[");
        sb.append(code);
        sb.append(":");
        sb.append(message);
        sb.append("]");
        return sb.toString();
    }

}
