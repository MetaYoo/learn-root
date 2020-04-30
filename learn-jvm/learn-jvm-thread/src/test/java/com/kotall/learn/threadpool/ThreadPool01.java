package com.kotall.learn.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool01 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(() -> {
            System.out.println("测试测试ss");
        });

//        executorService.shutdown();


        /**
         *
         */
        executorService.execute(() -> {

        });
    }
}
