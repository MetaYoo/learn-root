package com.kotall.common.util;

import org.apache.log4j.Logger;

/**
 * 捕捉到异常的时候，我们通常会使用<code>logger.error("xxxx",e)</code>方式打印日常堆栈日志<br>
 * 但是这种方式会造成错误日志打印两遍，精益求精，日志也追求极致，错误日志全部使用本工具类输出
 * 
 * @author wenzhi.wang
 * @version $Id: ExceptionUtil.java, v 0.1 Jan 3, 2017 3:36:50 PM phoenix Exp $
 */
public class ExceptionUtil {
    /** logger */
    private static final Logger logger      = Logger.getLogger("COMMON-ERROR");

    /** logger */
    private static final Logger alertLogger = Logger.getLogger("COMMON-ALERT");

    /**
     * 禁用构造函数
     */
    private ExceptionUtil() {
        // 禁用构造函数
    }

    /**
     * 捕捉错误日志并输出到日志文件：common-error.log
     * 
     * @param e
     *            异常堆栈
     * @param message
     *            错误日志上下文信息描述，尽量带上业务特征
     */
    public static void error(Throwable e, Object... message) {
        logger.error(LoggerUtil.getLogString(message), e);
    }

    /**
     * 捕捉错误日志并输出到日志文件：common-error.log
     * 
     * @param e
     *            异常堆栈
     * @param message
     *            错误日志上下文信息描述，尽量带上业务特征
     */
    public static void error(Exception e, Object... message) {
        logger.error(LoggerUtil.getLogString(message), e);
    }

    /**
     * 捕捉错误日志并输出到日志文件：common-error.log
     * 
     * @param message
     *            错误日志上下文信息描述，尽量带上业务特征
     */
    public static void error(Object... message) {
        logger.error(LoggerUtil.getLogString(message));
    }

    /**
     * 捕捉错误日志并输出到日志文件：common-error.log
     * 
     * @param message
     *            错误日志上下文信息描述，尽量带上业务特征
     *            说明：对比error，此方法信息更加详细，请使用前确认下输出的日志信息对性能的影响
     */
    public static void errorDetail(Object... message) {
        logger.error(LoggerUtil.getDetailLogString(message));
    }

    /**
     * 捕捉错误日志并输出到日志文件：alert.de.log
     * 
     * @param e
     *            异常堆栈
     * @param message
     *            错误日志上下文信息描述，尽量带上业务特征
     */
    public static void alert(Throwable e, Object... message) {
        alertLogger.warn(LoggerUtil.getLogString(message), e);

    }

    /**
     * 捕捉错误日志并输出到日志文件：alert.de.log
     * 
     * @param message
     *            错误日志上下文信息描述，尽量带上业务特征
     */
    public static void alert(Object... message) {
        alertLogger.warn(LoggerUtil.getLogString(message));

    }
}
