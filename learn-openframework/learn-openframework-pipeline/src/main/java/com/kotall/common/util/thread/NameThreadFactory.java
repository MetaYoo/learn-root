package com.kotall.common.util.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @author zpwang
 * @version 1.0.0
 */
public class NameThreadFactory implements ThreadFactory {
    /** 线程组 */
    private final ThreadGroup   group;

    /** 当前池中的线程顺序号 */
    private final AtomicInteger threadNumber = new AtomicInteger(1);

    /** 线程前缀 */
    private final String        namePrefix;

    /** 线程池名称 */
    private final String        name;

    /**
     * 构造方法
     * @param name
     */
    public NameThreadFactory(String name) {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        if (null == name || "".equals(name.trim())) {
            this.name = "ThreadPool";
        } else {
            this.name = name.trim();
        }
        namePrefix = this.name + "-";
    }

    /** 
     * @see ThreadFactory#newThread(Runnable)
     */
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
        if (t.isDaemon()) {
            t.setDaemon(false);
        }
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }
}
