package com.kotall.learn.dp;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class ServerTest {

    public static void main(String[] args) throws Exception {
        new Thread(new Reactor(9999)).start();
    }
}
