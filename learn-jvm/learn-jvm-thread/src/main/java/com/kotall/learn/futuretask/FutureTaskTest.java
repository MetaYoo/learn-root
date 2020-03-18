package com.kotall.learn.futuretask;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * desc:
 *
 * @author zpwang
 * @date 2020/3/18 11:43
 * @since 1.0.0
 */
public class FutureTaskTest {
    public static void main(String[] args) throws Exception {
        Callable callable = () -> "hello world";
        FutureTask<String> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();
        System.out.println("Before FutureTask.get()");
        System.out.println("FutureTask.get() =>" + futureTask.get());
        System.out.println("After FutureTask.get()");

    }
}
