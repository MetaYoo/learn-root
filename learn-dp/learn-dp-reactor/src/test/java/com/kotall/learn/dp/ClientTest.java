package com.kotall.learn.dp;

import java.io.IOException;
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
public class ClientTest implements Runnable {

    private SocketChannel socketChannel;

    private Selector selector;

    @Override
    public void run() {
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9999));
            socketChannel.configureBlocking(false);

            socketChannel.register(selector, SelectionKey.OP_CONNECT);

            while (selector.select() > 0) {
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeys.iterator();
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    if (key.isReadable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        String content = "";
                        while (channel.read(buffer) > 0) {
                           buffer.flip();
                           content += buffer.toString();
                        }
                        System.out.println("===接收到的内容： " + content);
                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
       new Thread(new ClientTest()).start();
    }

}
