package com.kotall.learn.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * desc:
 *
 * @author zpwang
 * @create 2019/3/5 18:08
 * @since 1.0.0
 */
public class ThreadExTest {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i=0; i < 1; i++) {
            executorService.submit(() -> {
                int count = 0;
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " is running.");
                    count ++;
                    if (count == 100) {
                        TimeUnit.SECONDS.sleep(10);
                    }
                }
            });
        }

        TimeUnit.SECONDS.sleep(1000);
    }
}
