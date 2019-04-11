package com.kotall.learn.thread;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class ThreadExecutorTest {

    @Test
    public void testNewCachedThreadPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(() -> System.out.println("newCachedThreadExecutor"));
    }

    @Test
    public void testNewFixedThreadPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.submit(() -> System.out.println("newFixedThreadExecutor"));
    }

    @Test
    public void testNewSingleThreadPool() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> System.out.println("newSingleThreadExecutor"));
    }

    @Test
    public void testNewScheduledThreadPool() {
        ExecutorService executorService = Executors.newScheduledThreadPool(3);
        executorService.submit(() -> System.out.println("newScheduledThreadPool"));
    }

    @Test
    public void testScheduledThreadPool() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);
        executorService.scheduleAtFixedRate(() -> {
            System.out.println(Thread.currentThread().getName() + " execute !");
        }, 20, 3600, TimeUnit.SECONDS);
        while (true) {
            // todo
        }
    }

}
