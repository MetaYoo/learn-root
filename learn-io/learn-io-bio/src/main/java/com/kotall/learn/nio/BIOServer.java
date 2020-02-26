package com.kotall.learn.nio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServer {

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(3000);
        while (true) {
            // 等待接收客户端连接
            System.out.println("等待客户端连接:" + Thread.currentThread().getName());
            Socket socket = serverSocket.accept();
            System.out.println("有客户端连接:" + Thread.currentThread().getName());
            executorService.submit(() -> {
                handleSocket(socket);
            });
        }

    }

    private static void handleSocket(Socket socket) {
        try {
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            while (true) {
                System.out.println("等待接收数据:" + Thread.currentThread().getName());
                int len = inputStream.read(bytes);
                System.out.println("接收到数据 " + Thread.currentThread().getName());
                if (len != -1) {
                    System.out.println(new String(bytes, 0, len));
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
