/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.kotall.common.util.thread;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.kotall.common.util.LoggerUtil;

/**
 * 线程池管理器
 * @author wenzhi.wang
 * @version $Id: ThreadPoolManager.java, v 0.1 Jan 3, 2017 10:58:07 AM phoenix Exp $
 */
public class ThreadPoolManager {
    //~~~ 常量
    /**日志*/
    private static Logger                                         logger                   = Logger
        .getLogger("THREAD-POOL-MANAGE");

    /** 默认线程池名称 */
    private final static String                                   defaultThread            = "DEFAULT_Thread";

    /** 默认的拒绝处理器 */
    private static final RejectedExecutionHandler                 ABORT_POLICY             = new ThreadPoolExecutor.AbortPolicy();

    //~~~ 静态成员

    /** 系统中管理的所有线程池 */
    private static final Map<String, ThreadPoolExecutor>          THREAD_POOLS             = new ConcurrentHashMap<String, ThreadPoolExecutor>();

    /** 系统中管理的所有调度线程池 */
    private static final Map<String, ScheduledThreadPoolExecutor> SCHEDULERED_THREAD_POOLS = new ConcurrentHashMap<String, ScheduledThreadPoolExecutor>();

    /**
     * 初始化，系统中需要使用的线程池，请定义到里面进行初始化
     */
    private static void init() {
        /** 线程执行器 */
        ThreadPoolExecutor ASYN_COMMON_THREADPOOL = applyThreadPool(
            ThreadNameConstant.ASYN_COMMON_THREADPOOL, 20, 20, 200);
        THREAD_POOLS.put(ThreadNameConstant.ASYN_COMMON_THREADPOOL, ASYN_COMMON_THREADPOOL);

        /** 线程执行器 */
        ThreadPoolExecutor defaultExec = applyThreadPool(defaultThread, 20, 200, 800);
        THREAD_POOLS.put(defaultThread, defaultExec);
    }

    /**
     * 获取线程，如果需要指定线程，请在init方法里自己初始化
     * @param threadName
     * @return
     */
    public static ThreadPoolExecutor getThread(String threadName) {
        if (StringUtils.isBlank(threadName) || THREAD_POOLS.get(threadName) == null) {
            return THREAD_POOLS.get(defaultThread);
        } else {
            return THREAD_POOLS.get(threadName);
        }

    }

    /**
     * 获取默认线程池。
     * @return
     */
    public static ThreadPoolExecutor getThread() {
        return getThread(null);
    }

    //~~~ 静态方法
    static {

        init();

        //执行调度任务的线程池
        ScheduledExecutorService scheExecutor = Executors.newScheduledThreadPool(1,
            new NameThreadFactory("SchedulerTask"));
        scheExecutor.scheduleAtFixedRate(new Runnable() {

            public void run() {

                //打印线程池日志
                for (Map.Entry<String, ThreadPoolExecutor> executor : THREAD_POOLS.entrySet()) {

                    LoggerUtil.info(logger, "线程池:", executor.getKey(), "配置的最大线程数目:",
                        executor.getValue().getMaximumPoolSize(), ",当前存活的线程:",
                        executor.getValue().getPoolSize(), ",当前正在执行的线程：",
                        executor.getValue().getActiveCount(), ",当前队列:使用:",
                        executor.getValue().getQueue().size(), " 剩余:",
                        executor.getValue().getQueue().remainingCapacity());

                }
                for (Map.Entry<String, ScheduledThreadPoolExecutor> executor : SCHEDULERED_THREAD_POOLS
                    .entrySet()) {
                    LoggerUtil.info(logger, "线程池:", executor.getKey(), "配置的最大线程数目:",
                        executor.getValue().getMaximumPoolSize(), ",当前存活的线程:",
                        executor.getValue().getPoolSize(), ",当前正在执行的线程:",
                        executor.getValue().getActiveCount(), ",当前队列:使用:",
                        executor.getValue().getQueue().size(), " 剩余:",
                        executor.getValue().getQueue().remainingCapacity());
                }

            }
        }, 10, 20000l, TimeUnit.MILLISECONDS);
    }

    /**
     * 申请一个线程池, 不预热.
     * <p>
     * 如果同名的线程池已经申请过且没有被关闭, 则返回已有的实例. 需要注意, 如果返回的是已有的实例, 
     * 则其配置为原配置.
     * 
     * @param name 线程池唯一名称
     * @param coreSize 核心线程数
     * @param maxSize 最大线程数
     * @param queueSize 队列长度
     * @param handler 拒绝处理器
     * @return
     * @throws IllegalArgumentException 线程池名称为空, 或线程池配置不合法
     */
    private static ThreadPoolExecutor applyThreadPool(String name, int coreSize, int maxSize,
                                                      int queueSize,
                                                      RejectedExecutionHandler handler) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("线程池名称不允许为空");
        }

        // 清空所有已关闭的线程池
        purge();

        String validName = name.trim();
        ThreadPoolExecutor tp = THREAD_POOLS.get(validName);
        if (null == tp) {
            if (0 == queueSize) {
                // 这里做一个优化, 对于0队列长度的线程池, 直接使用SynchronousThreadPool代替
                THREAD_POOLS.put(validName,
                    new ThreadPoolExecutor(coreSize, maxSize, 60, TimeUnit.SECONDS,
                        new SynchronousQueue<Runnable>(),
                        new NameThreadFactory(validName + "-pool"), handler));
            } else {
                THREAD_POOLS.put(validName,
                    new ThreadPoolExecutor(coreSize, maxSize, 60, TimeUnit.SECONDS,
                        new LinkedBlockingQueue<Runnable>(queueSize),
                        new NameThreadFactory(validName + "-pool"), handler));
            }
        }
        tp = THREAD_POOLS.get(validName);
        return tp;
    }

    /**
     * 申请一个线程池, 不预热.
     * <p>
     * 如果线程池忙, 拒绝请求, 默认抛出{@link java.util.concurrent.RejectedExecutionException RejectedExecutionException}异常.
     * <p>
     * 如果同名的线程池已经申请过且没有被关闭, 则返回已有的实例. 需要注意, 如果返回的是已有的实例, 
     * 则其配置为原配置.
     * 
     * @param name 线程池唯一名称
     * @param coreSize 核心线程数
     * @param maxSize 最大线程数
     * @param queueSize 队列长度
     * @return
     * @throws IllegalArgumentException 线程池名称为空, 或线程池配置不合法
     */
    private static ThreadPoolExecutor applyThreadPool(String name, int coreSize, int maxSize,
                                                      int queueSize) {
        return applyThreadPool(name, coreSize, maxSize, queueSize, ABORT_POLICY);
    }

    /**
     * 移除所有已被shutdown的线程池.
     */
    public static void purge() {
        synchronized (THREAD_POOLS) {
            for (String poolName : THREAD_POOLS.keySet()) {
                ThreadPoolExecutor pool = THREAD_POOLS.get(poolName);
                if (pool.isShutdown()) {
                    THREAD_POOLS.remove(poolName);
                }
            }
        }
        synchronized (SCHEDULERED_THREAD_POOLS) {
            for (String poolName : SCHEDULERED_THREAD_POOLS.keySet()) {
                ThreadPoolExecutor pool = SCHEDULERED_THREAD_POOLS.get(poolName);
                if (pool.isShutdown()) {
                    SCHEDULERED_THREAD_POOLS.remove(poolName);
                }
            }
        }
    }

    /**
     * 对线程池进行预热.
     * <p>
     * 线程池在刚刚创建出来时, 是一个线程都没有的, 不能立即开始全能力工作.
     * <p>
     * 执行预热即向线程池投入若干空跑的任务, 促使线程池创建线程.
     * 
     * @param tp
     * @param count
     */
    public static void wormUpThreadPool(ThreadPoolExecutor tpe, int count) {
        if (null == tpe) {
            return;
        }
        int i = count;
        while (i > 0 && tpe.prestartCoreThread()) {
            i--;
        }
    }
}
