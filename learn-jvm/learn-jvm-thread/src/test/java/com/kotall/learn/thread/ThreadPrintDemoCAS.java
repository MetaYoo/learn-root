package com.kotall.learn.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Java 多线程中两个线程交替执行，一个输出偶数，一个输出奇数
 * 基于 CAS 关键字实现
 */
public class ThreadPrintDemoCAS {

    static AtomicInteger casNum = new AtomicInteger(0);
    static volatile boolean flag = true;


    public static void main(String[] args) {
        // 输出偶数 even
        Thread t1 = new Thread(() -> {
            for (; 100 > casNum.get(); ) {
                if (flag && (casNum.get() == 0 || casNum.incrementAndGet() % 2 == 0)) {
                    System.out.println(casNum.get());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    flag = false;
                }
            }
        });

        // 输出奇数 odd
        Thread t2 = new Thread(() -> {
            for (; 100 > casNum.get(); ) {
                if (!flag && (casNum.incrementAndGet() % 2 != 0)) {
                    System.out.println(casNum.get());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    flag = true;
                }
            }
        });

        t1.start();
        t2.start();
    }
}
