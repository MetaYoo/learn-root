package com.kotall.learn.nio.demo3;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class Server {
    public static void main(String[] args) {
        try {
            Selector selector = Selector.open();
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.configureBlocking(false);
            ssc.bind(new InetSocketAddress(20880));
            ssc.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("服务端正在监听 20880 端口：");
            while (true) {
                if (selector.select() > 0) {
                    for (SelectionKey key : selector.selectedKeys()) {
                        selector.selectedKeys().remove(key);
                        if (key.isAcceptable()) {
                            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                            SocketChannel sc = serverSocketChannel.accept();
                            sc.configureBlocking(false);
                            sc.register(selector, SelectionKey.OP_READ);
                        }

                        if (key.isReadable()) {
                            SocketChannel sc = (SocketChannel) key.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(1024);

                            sc.read(buffer);
                            buffer.flip();
                            byte[] bytes = new byte[buffer.remaining()];
                            buffer.get(bytes);
                            String request = new String(bytes, "UTF-8");
                            System.out.println("客户端请求：" + request);

                            String response = "hello client!";
                            buffer.clear();
                            buffer.put(response.getBytes());
                            buffer.flip();
                            sc.write(buffer);
                            //key.cancel();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
