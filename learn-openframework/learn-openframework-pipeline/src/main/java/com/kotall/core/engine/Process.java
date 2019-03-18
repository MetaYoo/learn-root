/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.kotall.core.engine;

import java.util.List;

import org.apache.log4j.Logger;

import com.kotall.common.util.ExceptionUtil;
import com.kotall.common.util.LoggerUtil;
import com.kotall.common.util.thread.ThreadNameConstant;
import com.kotall.common.util.thread.ThreadPoolManager;
import com.kotall.core.context.BusinessContext;

/**
 * Process具体实现，支持事务和异步处理
 * @author wenzhi.wang
 * @version $Id: Process.java, v 0.1 Dec 31, 2016 11:19:35 AM phoenix Exp $
 */
public class Process {
    private static Logger logger = Logger.getLogger(Process.class);
    /** 是否使用事务 */
    private boolean       isTran = false;

    /** 是否是异步 */
    private boolean       isAsyn = false;

    private List<IAction> actions;

    //spring事务模板，有ds后进行相关配置
    //    private TransactionTemplate transactionTemplate;

    /**
     * Setter method for property <tt>isTran</tt>.
     * 
     * @param isTran value to be assigned to property isTran
     */
    public void setTran(boolean isTran) {
        this.isTran = isTran;
    }

    public void setAsyn(boolean isAsyn) {
        this.isAsyn = isAsyn;
    }

    /**
     * Setter method for property <tt>actions</tt>.
     * 
     * @param actions value to be assigned to property actions
     */
    public void setActions(List<IAction> actions) {
        this.actions = actions;
    }

    public void exec(final BusinessContext context) throws Exception {
        asynExecs(context);
    }

    /**
     * 事务执行单元
     * @param context
     * @throws Exception
     */
    private void asynExecs(final BusinessContext context) throws Exception {
        if (isAsyn) {
            LoggerUtil.debug(logger, "开启异步线程");
            //起异步线程
            ThreadPoolManager.getThread(ThreadNameConstant.ASYN_COMMON_THREADPOOL)
                .execute(new Runnable() {

                    public void run() {

                        try {
                            transExec(context);
                        } catch (Throwable e) {
                            ExceptionUtil.error("异步任务支持异常", e);
                            throw new RuntimeException(e);
                        }

                    }
                });

        } else {
            transExec(context);
        }
    }

    private void transExec(final BusinessContext context) throws Exception {
        //使用springtanssaction 待ds完备后开放这块代码
        if (isTran) {
            LoggerUtil.debug(logger, "开启事务模板");
            //
            //            try {
            //                Exception exception = (Exception) transactionTemplate
            //                    .execute(new TransactionCallback() {
            //
            //                        @Override
            //                        public Object doInTransaction(TransactionStatus transactionStatus) {
            //                            try {
            //                                // 执行业务逻辑 
            //                                execs();
            //                            } catch (Exception e) {
            //                                transactionStatus.setRollbackOnly();
            //                                return e;
            //                            }
            //                            return null;
            //                        }
            //                    });
            //
            //                if (exception != null) {
            //                    throw exception;
            //                }
            //            } finally {
            //                //TODO 记录日志
            //            }

            execs(context);
        } else {
            execs(context);
        }
    }

    /**
     * 执行process的
     * @param context
     * @throws Exception
     */
    private void execs(BusinessContext context) throws Exception {

        for (IAction action : actions) {
            action.exec(context);
        }
    }
}
