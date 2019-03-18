/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.kotall.common.util.enums;

/**
 * <p>用于约束Enum的代码风格</p>
 * @author wenzhi.wang
 * @version $Id: BaseEnum.java, v 0.1 Dec 31, 2016 11:10:46 AM phoenix Exp $
 */
public interface BaseEnum {
    /**
     * <p>获取枚举编码</p>
     * @return
     */
    String getCode();

    /**
     * <p>获取枚举消息</p>
     * @return
     */
    String getMessage();
}
