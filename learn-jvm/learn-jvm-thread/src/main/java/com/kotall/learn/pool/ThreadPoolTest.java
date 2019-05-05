package com.kotall.learn.pool;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.TimeUnit;

/**
 * desc:
 *
 * @author zpwang
 * @date 2019/4/30 11:48
 * @since 1.0.0
 */
public class ThreadPoolTest {

    public static void main(String[] args) throws Exception {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(1);
        pool.setMaxPoolSize(4);
        pool.setQueueCapacity(100);
        pool.setThreadNamePrefix("pool-");

        pool.initialize();
        for (int i =0; i < 500; i++) {
            final int j = i;
            pool.submit(() ->System.out.println(Thread.currentThread().getName() + " " + j));
        }

        TimeUnit.SECONDS.sleep(1000);

    }
}
