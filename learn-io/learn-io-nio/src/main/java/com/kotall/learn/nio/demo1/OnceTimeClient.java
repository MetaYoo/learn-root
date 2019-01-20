package com.kotall.learn.nio.demo1;

import java.io.ByteArrayOutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * 客户端
 * 客户端只发送一次请求
 *
 * @author zpwang
 * @version 1.0.0
 */
public class OnceTimeClient {

    public static void main(String[] args) {
        try {
            Selector selector = Selector.open();
            SocketChannel socket = SocketChannel.open();
            socket.configureBlocking(false);
            boolean isConnected = socket.connect(new InetSocketAddress(20880));

            if (!isConnected) {
                socket.register(selector, SelectionKey.OP_CONNECT);
            } else {
                socket.register(selector, SelectionKey.OP_READ);
                // 连接成功，写数据
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                buffer.put("hello world !".getBytes());
                buffer.flip();
                socket.write(buffer);
            }

            while (selector.select() > 0) {
                for (SelectionKey key : selector.selectedKeys()) {
                    selector.selectedKeys().remove(key);
                    if (key.isValid()) {

                        // =============================
                        if (key.isConnectable()) {
                            if (socket.finishConnect()) {
                                // 成功连接上
                                // 先注册好读监听
                                socket.register(selector, SelectionKey.OP_READ);
                                // 写数据
                                ByteBuffer buffer = ByteBuffer.allocate(1024);
                                buffer.put("hello world !".getBytes());
                                buffer.flip();
                                socket.write(buffer);
                            }
                        }

                        if (key.isReadable()) {
                            // 可读
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            SocketChannel sc = (SocketChannel) key.channel();
                            ByteArrayOutputStream bos = new ByteArrayOutputStream();
                            while (sc.read(buffer) > 0) {
                                buffer.flip();
                                while (buffer.hasRemaining()) {
                                    bos.write(buffer.get());
                                }
                                buffer.clear();
                            }
                            String response = new String(bos.toByteArray(), "UTF-8");
                            bos.close();
                            System.out.println("服务端响应数据：" + response);

                            key.cancel();
                            key.channel().close();
                            break;
                        }
                        // =============================
                    }
                }
            }

            selector.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
