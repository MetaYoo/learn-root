/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.kotall.core.engine;

import java.util.List;
import java.util.Map;

import com.kotall.common.util.ExceptionUtil;
import com.kotall.core.context.BusinessContext;
import com.kotall.core.context.ProcessResult;
import com.kotall.core.context.ResultCode;

/**
 * 
 * @author wenzhi.wang
 * @version $Id: ProcessEngineImpl.java, v 0.1 Dec 31, 2016 11:25:52 AM phoenix Exp $
 */
public class ProcessEngineImpl implements ProcessEngine {

    /** 流程process处理器**/
    private Map<String, List<Process>> processMap;

    /** 
     * @see ProcessEngine#process(BusinessContext)
     */

    public ProcessResult process(BusinessContext context) {
        ProcessResult result = null;

        try {

            ProcessHolder.createProcessContext(context);
            String processActionType = context.getBizType();
            if (processActionType == null || processActionType.equals("")) {
                ExceptionUtil.alert("业务类型未设置");
                throw new Exception("业务类型未设置");
            }

            List<Process> processList = processMap.get(context.getBizType());

            if (processList == null || processList.isEmpty()) {
                ExceptionUtil.alert("未找到对应的流程，请设置正确的业务类型", processActionType);
                throw new Exception("未找到对应的流程，请设置正确的业务类型" + processActionType);
            }
            for (Process process : processList) {
                process.exec(ProcessHolder.getProcessContext());
            }

            result = new ProcessResult(ResultCode.SUCCESS, context);
            return result;

        } catch (Exception t) {
            ExceptionUtil.alert("系统异常", t);
            result = new ProcessResult(ResultCode.UNKNOW, context);
            return result;

        } finally {
            ProcessHolder.clear();
            //记录日志
        }

    }

    /**
     * Setter method for property <tt>processMap</tt>.
     * 
     * @param processMap value to be assigned to property processMap
     */
    public void setProcessMap(Map<String, List<Process>> processMap) {
        this.processMap = processMap;
    }

}
