package com.kotall.learn.nio;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class ServerBoot {
    public static void main(String[] args) {
        new Server(8000).start();
    }
}
