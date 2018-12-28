package com.kotall.learn.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * 回环栅栏测试
 * 字面意思回环栅栏，通过它可以实现让一组线程等待至某个状态之后再全部同时执行。叫做回环是因为当所有等待线程都被释放以后，CyclicBarrier可以被重用。我们暂且把这个状态就叫做barrier，当调用await()方法之后，线程就处于barrier了。
 *
 * @author zpwang
 * @version 1.0.0
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {
        //CyclicBarrier barrier = new CyclicBarrier(4);
        CyclicBarrier barrier = new CyclicBarrier(4, () -> {
            System.out.println("所有线程执行完毕后，从所有线程中选出一个执行此逻辑， 当前线程：" + Thread.currentThread().getName());
        });
        for (int i = 0; i < 4; i++) {
            new Thread(new CyclicBarrierTask(barrier)).start();
        }

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("CyclicBarrier重用");
        for (int i = 0; i < 4; i++) {
            new Thread(new CyclicBarrierTask(barrier)).start();
        }
    }
}

class CyclicBarrierTask implements Runnable {
    CyclicBarrier barrier = null;

    public CyclicBarrierTask(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        System.out.println("线程：" + Thread.currentThread().getName() + " 正在执行任务。。。");
        try {
            TimeUnit.SECONDS.sleep(10);
            System.out.println("线程：" + Thread.currentThread().getName() + " 执行完毕，等待其他线程执行完毕。。。");
            this.barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("所有线程执行完毕，线程：" + Thread.currentThread().getName() + " 继续执行其他任务！");
    }
}
