package com.kotall.learn.bio;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class Client {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8000;
    private static final int SLEEP_TIME = 5;

    public static void main(String[] args) {
        try {
            final Socket socket = new Socket(HOST, PORT);
            new Thread(() -> {
                System.out.println("客户端启动成功");
                while (true) {
                    try {
                        String message = "hello world";
                        System.out.println("客户端发送数据：" + message);
                        socket.getOutputStream().write(message.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    sleep();
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sleep() {
        try {
            TimeUnit.SECONDS.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
