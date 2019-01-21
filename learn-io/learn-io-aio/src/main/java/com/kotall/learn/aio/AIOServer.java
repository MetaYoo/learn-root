package com.kotall.learn.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.TimeUnit;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class AIOServer {
    public static void main(String[] args) throws Exception {
        try {
            AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open();
            server.bind(new InetSocketAddress(20880));
            server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
                @Override
                public void completed(AsynchronousSocketChannel socketChannel, Object attachment) {
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    socketChannel.read(buffer);
                    buffer.flip();
                    String request = new String(buffer.array());
                    System.out.println("===>" + request);
                }

                @Override
                public void failed(Throwable exc, Object attachment) {
                    System.out.println(exc);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        TimeUnit.SECONDS.sleep(1000);
    }
}
