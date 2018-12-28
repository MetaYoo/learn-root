package com.kotall.learn.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 5; i++) {
            new Thread(new SemaphoreTask(semaphore)).start();
        }
    }
}

class SemaphoreTask implements Runnable {
    Semaphore semaPhore = null;

    public SemaphoreTask(Semaphore semaPhore) {
        this.semaPhore = semaPhore;
    }

    @Override
    public void run() {
        try {
            semaPhore.acquire();
            System.out.println("线程：" + Thread.currentThread().getName() + "正在执行任务！");
            TimeUnit.SECONDS.sleep(2);
            System.out.println("线程：" + Thread.currentThread().getName() + "任务执行完毕，释放 Semaphore！");
            semaPhore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}