package com.kotall.learn.threadlocal;

import org.junit.Test;

import java.util.concurrent.*;

public class ThreadLocalTest {

    @Test
    public void testThread() {

        Thread thread = Thread.currentThread();
        
    }

    @Test
    public void testThreadLocal() {
        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.set("tttt");
    }

    @Test
    public void testMultiThread() throws Exception {
        ExecutorService executorService = new ThreadPoolExecutor(1, 3, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(1200));
        for (int i=0; i < 1000; i++) {
            executorService.submit(new Task(i));
        }

        TimeUnit.SECONDS.sleep(100000);

    }


    class Task implements Runnable {
        int i;
        public Task(int i) {
            this.i = i;
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + i);
        }
    }

}
