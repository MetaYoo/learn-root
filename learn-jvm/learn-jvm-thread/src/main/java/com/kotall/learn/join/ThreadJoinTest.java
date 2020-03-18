package com.kotall.learn.join;

/**
 * desc:
 *
 * @author zpwang
 * @date 2020/3/18 10:39
 * @since 1.0.0
 */
public class ThreadJoinTest {

    /**
     * Thread.join() 使被掉函数执行完毕后再执行主调线程
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(() -> System.out.println("A线程执行！"));
        Thread t2 = new Thread(() -> {
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("A线程执行完毕，B线程开始执行！");
        });
        t1.start();
        t2.start();
        t2.join();
        System.out.println("子线程已经执行完毕，开始执行主线程！");
    }
}
