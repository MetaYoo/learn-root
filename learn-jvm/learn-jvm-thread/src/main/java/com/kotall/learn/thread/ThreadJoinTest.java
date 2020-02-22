package com.kotall.learn.thread;

public class ThreadJoinTest {

    public static void main(String[] args) {
        Thread previous = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Demo(previous), String.valueOf(i));
            thread.start();
            previous = thread;
            System.out.println("线程：" + Thread.currentThread().getName() + " 启动完毕！");
        }
    }

    static class Demo implements Runnable {
        private Thread thread;

        public Demo(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                this.thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "terminate.");
        }
    }
}


