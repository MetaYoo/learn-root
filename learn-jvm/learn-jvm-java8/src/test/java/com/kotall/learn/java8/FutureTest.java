package com.kotall.learn.java8;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class FutureTest {

    @Test
    public void test1() {Bean
        // 变换结果
        String result = CompletableFuture.supplyAsync(() -> {
            return "Hello ";
        }).thenApplyAsync(v -> v + "world").join();
        System.out.println(result);

        // 消费结果
    }

    @Test
    public void test2() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = new CompletableFuture<>();
        new Thread(() -> {
            try {
                Thread.sleep(10000);
                future.complete("hello world");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        String result = future.get();
        System.out.println(result);
        //DeferredResult

    }

}
