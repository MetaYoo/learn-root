package com.kotall.learn.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者问题
 * 生产者： 队列没有满， 我可以继续生产， 需要保持 notFull 条件
 * 消费者： 队列没有空， 我可以继续消费， 需要保持 notEmpty 条件
 * @author zpwang
 * @version 1.0.0
 */
public class ConditionTest {
    public static void main(String[] args) {
        BoundedBuffer buffer = new BoundedBuffer();

        Thread producer = new Thread(() -> {
            for (int i=0; i < 100; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    buffer.put(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i=0; i < 100; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    buffer.take();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        producer.start();
        consumer.start();

    }
}

class BoundedBuffer {
    final ReentrantLock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final Object[] items = new Object[5];
    int putPtr, takePtr, count;

    public void put(Object x) throws Exception {
        lock.lock();
        System.out.println("生产者获得锁，开始生产数据");
        try {
            while (count == items.length) {
                System.out.println("队列已经装满，生产者等待中，并通知消费者消费...");
                notFull.await();
            }
            items[putPtr] = x;
            count++;
            if (++putPtr == items.length) {
                putPtr = 0;
            }

            notEmpty.signal();

        } finally {
            System.out.println("生产者释放锁");
            lock.unlock();
        }

    }

    public Object take() throws Exception {
        lock.lock();
        System.out.println("消费者获得锁， 开始消费数据");
        try {
            while (count == 0) {
                notEmpty.await();
            }
            Object o = items[takePtr];
            if (++takePtr == items.length) {
               takePtr = 0;
            }
            count--;
            notFull.signal();
            return o;
        } finally {
            System.out.println("消费者释放锁");
           lock.unlock();
        }
    }
}
