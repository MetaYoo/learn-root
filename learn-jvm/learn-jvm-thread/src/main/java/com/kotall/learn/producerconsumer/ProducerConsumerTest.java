package com.kotall.learn.producerconsumer;

import java.util.concurrent.TimeUnit;

/**
 * desc:
 *
 * @author zpwang
 * @date 2020/3/18 11:51
 * @since 1.0.0
 */
public class ProducerConsumerTest {

    public static void main(String[] args) throws Exception {
        Object lock = new Object();
        Thread p = new Thread(new Producer(lock));
        Thread c = new Thread(new Consumer(lock));

        p.start();
        c.start();

        TimeUnit.SECONDS.sleep(10);
    }

    private static boolean isFull = false;

    static class Producer implements Runnable {
        Object lock;

        public Producer(Object lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    if (!isFull) {
                        System.out.println("P");
                        isFull = true;
                    } else {
                        try {
                            // 通知消费者
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    lock.notify();
                }

            }
        }
    }


    static class Consumer implements Runnable {
        Object lock;

        public Consumer(Object lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    if (isFull) {
                        System.out.println("C");
                        isFull = false;
                    } else {
                        try {
                            // 通知生产者
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    lock.notify();
                }

            }
        }
    }
}
