/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.kotall.core.engine;

import com.kotall.core.context.BusinessContext;
import com.kotall.core.context.ProcessResult;

/**
 * 处理器流程引擎接口
 * @author wenzhi.wang
 * @version $Id: ProcessEngine.java, v 0.1 Dec 31, 2016 11:24:24 AM phoenix Exp $
 */
public interface ProcessEngine {

    public ProcessResult process(BusinessContext context);
}
