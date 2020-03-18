package com.kotall.learn.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * desc:
 *
 * @author zpwang
 * @date 2020/3/18 11:30
 * @since 1.0.0
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws Exception {
        CountDownLatch latch = new CountDownLatch(3);
        Thread t1 = new Thread(() -> {
            System.out.println("A");
            latch.countDown();
        });
        Thread t2 = new Thread(() -> {
            System.out.println("B");
            latch.countDown();
        });
        Thread t3 = new Thread(() -> {
            System.out.println("C");
            latch.countDown();
        });

        t1.start();
        t2.start();
        t3.start();
        latch.await();
        System.out.println("所有子线程都执行完毕，主线程开始执行");
    }
}
