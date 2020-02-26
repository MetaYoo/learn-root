package com.kotall.learn.nio;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class ClientHandler {
    private Socket socket = null;

    public ClientHandler(Socket client) {
        this.socket = client;
    }

    public void start() {
        System.out.println("新客户端接入");
        new Thread(() -> doStart()).start();
    }

    public void doStart() {
        try {
            InputStream is = this.socket.getInputStream();
            while (true) {
                byte[] data = new byte[1024];
                int len;
                while ((len = is.read(data)) != -1) {
                    String message = new String(data, 0, len);
                    System.out.println("客户端传来消息：" + message);
                    socket.getOutputStream().write(data);
                }
            }
        } catch (IOException e) {
            System.out.println("客户端处理异常！");
        }

    }


}
