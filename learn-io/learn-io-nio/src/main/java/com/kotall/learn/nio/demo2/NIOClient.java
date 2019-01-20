package com.kotall.learn.nio.demo2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class NIOClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 20880);
        OutputStream out = socket.getOutputStream();

        String string = "hello world";
        out.write(string.getBytes());
        out.close();

        InputStream is = socket.getInputStream();
        byte[] bytes = new byte[1024];
        is.read(bytes);
        System.out.println("响应数据：" + new String(bytes));


        socket.close();
    }
}
