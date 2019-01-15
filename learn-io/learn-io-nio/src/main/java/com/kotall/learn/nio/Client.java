package com.kotall.learn.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class Client {
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);


        Selector selector = Selector.open();
        boolean isConnected = socketChannel.connect(new InetSocketAddress(8000));

        if (isConnected) {
            socketChannel.register(selector, SelectionKey.OP_READ);
        } else {
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }

        while (true) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> it = keys.iterator();
            while (it.hasNext()) {
                SelectionKey key = it.next();

                it.remove();
                if (key.isValid()) {
                    SocketChannel sc = (SocketChannel) key.channel();
                    if (key.isConnectable()) {
                        if (sc.finishConnect()) {
                            sc.register(selector, SelectionKey.OP_READ);
                            // 写数据
                            String message = "QUERY DATE!";
                            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                            writeBuffer.put(message.getBytes("UTF-8"));
                            writeBuffer.flip();
                            sc.write(writeBuffer);
                        }
                    }

                    System.out.println(key.isReadable());

                    if (key.isReadable()) {
                        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                        int readLength = sc.read(readBuffer);
                        if (readLength > 0) {
                            readBuffer.flip();
                            byte[] bytes = new byte[readBuffer.remaining()];
                            readBuffer.get(bytes);
                            System.out.println("接收到服务端响应数据：" + new String(bytes, "UTF-8"));
                        }
                    }
                }
                key.cancel();

            }
        }

    }
}
