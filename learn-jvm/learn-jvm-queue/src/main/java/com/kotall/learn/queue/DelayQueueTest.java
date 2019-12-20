package com.kotall.learn.queue;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * desc:
 *
 * @author zpwang
 * @date 2019/12/20 9:59
 * @since 1.0.0
 */
public class DelayQueueTest {

    public static void main(String[] args) {
        // 创建延时队列
        DelayQueue<DelayedMessage> queue = new DelayQueue<>();
        // 添加延时消息,m1 延时3s
        DelayedMessage m1 = new DelayedMessage(1, "world", 3000);
        // 添加延时消息,m2 延时10s
        DelayedMessage m2 = new DelayedMessage(2, "hello", 10000);
        //将延时消息放到延时队列中
        queue.offer(m2);
        queue.offer(m1);
        // 启动消费线程 消费添加到延时队列中的消息，前提是任务到了延期时间
        ExecutorService exec = Executors.newFixedThreadPool(1);
        exec.execute(new Consumer(queue));
        exec.shutdown();
    }
}

class Consumer implements Runnable {
    /**
     * 延时队列 ,消费者从其中获取消息进行消费
     */
    private DelayQueue<DelayedMessage> queue;

    public Consumer(DelayQueue<DelayedMessage> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                DelayedMessage take = queue.take();
                Date t = new Date(TimeUnit.MILLISECONDS.convert(take.getExecuteTime(), TimeUnit.NANOSECONDS));
                System.out.println("消费消息id：" + take.getId() + " 消息体：" + take.getBody() + ",时间：" + t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}