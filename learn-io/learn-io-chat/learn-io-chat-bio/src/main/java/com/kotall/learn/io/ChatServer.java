package com.kotall.learn.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * desc:
 *
 * @author zpwang
 * @date 2020/2/26 10:36
 * @since 1.0.0
 */
public class ChatServer {

    private static Map<Integer, Socket> socketMap = new ConcurrentHashMap<>();
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8000, 1024);
        while (true) {
            // 一直阻塞，知道接收到socket连接
            System.out.println("等待客户端连接请求...");
            Socket socket = serverSocket.accept();
            System.out.println("接收到客户端连接请求：" + socket.getPort());
            socketMap.putIfAbsent(socket.getPort(), socket);
            executorService.submit(() -> {
                try {
                    handleSocket(socket);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static void handleSocket(Socket socket) throws Exception {
        try {
            InputStream inputStream = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            System.out.println("读取客户端数据...");
            // 阻塞等待客户端发送数据
            String msg = br.readLine();
            while (msg != null) {
                if ("bye".equals(msg)) {
                    break;
                }
                System.out.println("客户端【" + socket.getPort() + "】：" + msg);
                // 转发数据到其他客户端
                for (Map.Entry<Integer, Socket> entry : socketMap.entrySet()) {
                    Integer key = entry.getKey();
                    Socket value = entry.getValue();
                    if (!key.equals(socket.getPort())) {
                        try {
                            OutputStream outputStream = value.getOutputStream();
                            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
                            bw.write("客户端【" + socket.getPort() + "】：" + msg.toUpperCase() + "\r\n");
                            bw.flush();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                msg = br.readLine();
            }
            br.close();
            inputStream.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
