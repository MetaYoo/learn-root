package com.kotall.learn.cyclebarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * desc:
 *
 * @author zpwang
 * @date 2020/3/18 11:35
 * @since 1.0.0
 */
public class CycleBarrierTest {

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3);

        Random random = new Random();
        Thread t1 = new Thread(() -> {
            try {
                System.out.println("before A...");
                TimeUnit.MILLISECONDS.sleep(random.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("A");
        });

        Thread t2 = new Thread(() -> {
            try {
                System.out.println("before B...");
                TimeUnit.MILLISECONDS.sleep(random.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

            System.out.println("B");
        });

        Thread t3 = new Thread(() -> {
            try {
                System.out.println("before C...");
                TimeUnit.MILLISECONDS.sleep(random.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

            System.out.println("C");
        });

        t1.start();
        t2.start();
        t3.start();
    }
}
