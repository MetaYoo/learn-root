package com.kotall.learn.thread;

/**
 * Java 多线程中两个线程交替执行，一个输出偶数，一个输出奇数
 * 基于 volatile 关键字实现
 */
public class ThreadPrintDemoVolatile {
    static volatile int num = 0;
    static volatile boolean flag = true;

    public static void main(String[] args) {
        // 输出偶数 even
        Thread t1 = new Thread(() -> {
            for (; 100 > num; ) {
                if (flag && (num == 0 || ++num % 2 == 0)) {
                    System.out.println(num);
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
            for (; 100 > num; ) {
                if (!flag && (++num % 2 != 0)) {
                    System.out.println(num);
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
