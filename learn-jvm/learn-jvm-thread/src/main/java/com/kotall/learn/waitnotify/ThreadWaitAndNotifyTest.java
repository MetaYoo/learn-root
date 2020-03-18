package com.kotall.learn.waitnotify;

import java.util.concurrent.TimeUnit;

/**
 * desc:
 *
 * @author zpwang
 * @date 2020/3/18 10:48
 * @since 1.0.0
 */
public class ThreadWaitAndNotifyTest {

    public static void main(String[] args) throws Exception {
        Object lock = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    System.out.println("A");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    System.out.println("B");
                    lock.notify();
                }
            }
        });

        t1.start();
        t2.start();

        Thread.sleep(200);
    }
}
