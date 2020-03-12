package com.kotall.learn.nio;


import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * desc:
 *
 * @author zpwang
 * @date 2020/2/26 14:13
 * @since 1.0.0
 */
public class NIOChatServer {

    public static void main(String[] args) throws Exception {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8000));
        // 配置为同步非阻塞
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            // 阻塞
            int select = selector.select();
            if (select > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();

                    if (key.isAcceptable()) {
                        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                        // 此时 accept就不是阻塞的了
                        SocketChannel channel = ssc.accept();
                        channel.configureBlocking(false);
                        System.out.println("接收到客户端【" + channel.getRemoteAddress() + "】请求：");
                        channel.register(selector, SelectionKey.OP_READ);
                    } else if (key.isReadable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        int r = channel.read(buffer);
                        if (r != -1) {
                            buffer.flip();
                            String msg = new String(buffer.array(), 0, buffer.limit(), "utf-8");
                            System.out.println("接收到客户端【" + channel.getRemoteAddress() + "】数据：" + msg);
                        } else {
                            channel.close();
                        }

                    }

                }
            }
        }
    }
}
