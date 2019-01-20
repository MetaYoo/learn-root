package com.kotall.learn.nio.demo1;

import java.io.ByteArrayOutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 服务端
 * 服务端只接收一次请求
 *
 * @author zpwang
 * @version 1.0.0
 */
public class OnceTimeServer {

    public static void main(String[] args) {

        try {
            Selector selector = Selector.open();
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.configureBlocking(false);
            ssc.bind(new InetSocketAddress(20880));
            ssc.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("服务端启动，监听端口号：20880");
            while (selector.select() > 0) {
                for (SelectionKey key : selector.selectedKeys()) {
                    selector.selectedKeys().remove(key);
                    // ======================================
                    if (key.isValid()) {
                        if (key.isAcceptable()) {
                            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                            SocketChannel sc = serverSocketChannel.accept();
                            sc.configureBlocking(false);
                            sc.register(selector, SelectionKey.OP_READ);
                        }

                        if (key.isReadable()) {
                            SocketChannel sc = (SocketChannel) key.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            ByteArrayOutputStream bos = new ByteArrayOutputStream();
                            while (sc.read(buffer) > 0) {
                                buffer.flip();
                                while (buffer.hasRemaining()) {
                                    bos.write(buffer.get());
                                }
                                buffer.clear();
                            }
                            String request = new String(bos.toByteArray(), "UTF-8");
                            bos.close();
                            System.out.println("接收到客户端请求：" + request);

                            String response = "I am from sun!";
                            buffer.clear();
                            buffer.put(response.getBytes("UTF-8"));
                            buffer.flip();
                            sc.write(buffer);
                            System.out.println("服务端返回：" + response);

                            key.cancel();
                            key.channel().close();
                        }
                    }
                    // ======================================
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
