package com.kotall.learn.jvm;

/**
 * -Xss2m 虚拟机栈
 * 虚拟机栈和本地方法栈溢出
 * @author zpwang
 * @version 1.0.0
 */
public class JvmStackTest2 {

    public void dontStop() {
        while (true) {}
    }

    public void startThread() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        JvmStackTest2 test = new JvmStackTest2();
        test.startThread();
    }
}
