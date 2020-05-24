package com.kotall.learn.countdownlatch;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest2 {
    private static final int MAX_THREADS = 1000;
    private CountDownLatch cdl = new CountDownLatch(MAX_THREADS);


    public void test() throws IOException {
        for (int i = 0; i < MAX_THREADS; i++) {
            Thread thread = new Thread(() -> {
                try {
                    cdl.countDown();
                    cdl.await();
                    System.out.println("业务操作");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }

        System.in.read();
    }

    public static void main(String[] args) throws IOException {
        new CountDownLatchTest2().test();
    }
}
