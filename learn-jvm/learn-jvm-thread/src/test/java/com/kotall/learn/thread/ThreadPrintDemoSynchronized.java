package com.kotall.learn.thread;

/**
 * Java 多线程中两个线程交替执行，一个输出偶数，一个输出奇数
 * 基于 synchronized 关键字实现
 */
public class ThreadPrintDemoSynchronized {

    public static void main(String[] args) {
        ThreadPrintDemoSynchronized demo = new ThreadPrintDemoSynchronized();
        Thread t1 = new Thread(demo::printEvenNumber);
        Thread t2 = new Thread(demo::printOddNumber);

        t1.start();
        t2.start();
    }

    /**
     * 打印偶数
     */
    public synchronized void printEvenNumber() {
        for (int i = 0; i < 100; i += 2) {
            System.out.println(i);
            // 挂起，让出CPU执行片
            this.notify();
            try {
                // 等待CPU时间片
                this.wait();
                // 休眠一下，防止打印速度过快导致混乱
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 打印奇数
     */
    public synchronized void printOddNumber() {
        for (int i = 1; i < 100; i += 2) {
            System.out.println(i);
            // 挂起，让出CPU时间片
            this.notify();
            try {
                // 等待CPU时间片
                this.wait();
                // 休眠一下，防止打印速度过快导致混乱
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
