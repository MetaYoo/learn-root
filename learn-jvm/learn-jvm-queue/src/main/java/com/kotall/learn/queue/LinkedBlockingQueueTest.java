package com.kotall.learn.queue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * desc:
 *
 * @author zpwang
 * @create 2019/3/5 17:23
 * @since 1.0.0
 */
public class LinkedBlockingQueueTest {

    LinkedBlockingQueue<Object> queue = new LinkedBlockingQueue<>(1000);

    public static void main(String[] args) throws Exception {
        LinkedBlockingQueueTest test = new LinkedBlockingQueueTest();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(() -> {
            test.produce(1000);
        });
        executorService.submit(() -> {
            test.produce(200);
        });
        executorService.submit(() -> {
            test.produce(300);
        });
        executorService.submit(() -> {
            test.produce(400);
        });
        executorService.submit(() -> {
            test.produce(500);
        });

        // ===============================
        executorService.submit(() -> {
            test.consume(200);
        });
        executorService.submit(() -> {
            test.consume(300);
        });
        executorService.submit(() -> {
            test.consume(400);
        });
        executorService.submit(() -> {
            test.consume(500);
        });


        TimeUnit.SECONDS.sleep(100000);

    }

    // 生产num个产品
    public void produce(int num) {
        // 如果仓库剩余容量为0
        if (queue.size() == 1000) {
            System.out.println("【库存量】:" + 1000 + "/t暂时不能执行生产任务!");
        }

        // 生产条件满足情况下，生产num个产品
        for (int i = 1; i <= num; ++i) {
            try {
                // 放入产品，自动阻塞
                queue.put(new Object());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("【现仓储量为】:" + queue.size());
        }
    }

    // 消费num个产品
    public void consume(int num) {
        // 如果仓库存储量不足
        if (queue.size() == 0) {
            System.out.println("【库存量】:0/t暂时不能执行生产任务!");
        }

        // 消费条件满足情况下，消费num个产品
        for (int i = 1; i <= num; ++i) {
            try {
                // 消费产品，自动阻塞
                queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("【现仓储量为】:" + queue.size());
    }
}
