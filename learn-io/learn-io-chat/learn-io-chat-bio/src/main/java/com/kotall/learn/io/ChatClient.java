package com.kotall.learn.io;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * desc:
 *
 * @author zpwang
 * @date 2020/2/26 12:11
 * @since 1.0.0
 */
public class ChatClient {

    public static void main(String[] args) throws Exception {
        // 阻塞连接
        Socket socket = new Socket("127.0.0.1", 8000);
        System.out.println("连接上服务器，端口号：" + socket.getPort());
        Thread t1 = new Thread(() -> {
            try {
                handleOutput(socket);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                handleInput(socket);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("聊天程序退出!");
    }

    private static void handleOutput(Socket socket) throws Exception {
        OutputStream outputStream = socket.getOutputStream();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println("【我】发送：" + line);
            line += "\r\n";
            outputStream.write(line.getBytes("utf-8"));
            outputStream.flush();
        }
    }

    private static void handleInput(Socket socket) throws Exception {
        InputStream inputStream = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String msg = br.readLine();
        while (msg != null) {
            System.out.println(msg);
            msg = br.readLine();
        }
    }
}
