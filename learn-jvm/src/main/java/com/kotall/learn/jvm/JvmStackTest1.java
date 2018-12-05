package com.kotall.learn.jvm;

/**
 * -Xoss20m 本地方法栈
 * -Xss20m 虚拟机栈
 * 虚拟机栈和本地方法栈溢出
 * @author zpwang
 * @version 1.0.0
 */
public class JvmStackTest1 {

    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JvmStackTest1 test = new JvmStackTest1();
        try {
            test.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length: " + test.stackLength);
            throw e;
        }

    }
}
