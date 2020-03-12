package com.kotall.learn.nio;


import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * desc:
 *
 * @author zpwang
 * @date 2020/2/27 16:48
 * @since 1.0.0
 */
public class NIOChatClient {

    public static void main(String[] args) throws Exception {
        Selector selector = Selector.open();
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress(8000));
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        while (true) {
            // 阻塞
            int selected = selector.select();
            if (selected > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isConnectable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        if (channel.finishConnect()) {
                            channel.register(selector, SelectionKey.OP_READ);
                            ByteBuffer src = ByteBuffer.allocate(1024);
                            src.clear();
                            src.put("hello world!".getBytes("utf-8"));
                            src.flip();
                            channel.write(src);
                        } else {
                            System.out.println("还未完成连接");
                        }
                        channel.register(selector, SelectionKey.OP_WRITE);
                    } else if (key.isReadable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer dst = ByteBuffer.allocate(1024);
                        channel.read(dst);
                        dst.flip();
                        System.out.println(new String(dst.array(), 0, dst.limit(), "utf-8"));
                    }/* else if (key.isWritable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        channel.register(selector, SelectionKey.OP_READ);
                        ByteBuffer src = ByteBuffer.allocate(1024);
                        src.clear();
                        src.put("hello world!".getBytes("utf-8"));
                        src.flip();
                        channel.write(src);
                    }*/
                    iterator.remove();
                }
            }
        }
    }
}
