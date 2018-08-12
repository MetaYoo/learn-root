package com.kotall.learn.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class Client implements Runnable {

    private static final int PORT = 9999;
    private static final int MAX_INPUT = 1024;

    @Override
    public void run() {
        try {
            Socket socket = new Socket("127.0.0.1", PORT);
            socket.getOutputStream().write("hi".getBytes());
            InputStream in = socket.getInputStream();
            byte[] b = new byte[MAX_INPUT];
            while (in.read(b) > -1) {
                System.out.println(new String(b));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Thread(new Client()).start();
    }
}
