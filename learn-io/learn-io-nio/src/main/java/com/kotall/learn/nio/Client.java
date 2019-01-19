package com.kotall.learn.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class Client {
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);


        Selector selector = Selector.open();
        boolean isConnected = socketChannel.connect(new InetSocketAddress(20880));

        if (isConnected) {
            socketChannel.register(selector, SelectionKey.OP_READ);
            String message = "QUERY DATE!";
            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
            writeBuffer.put(message.getBytes("UTF-8"));
            writeBuffer.flip();
            socketChannel.write(writeBuffer);
        } else {
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }

//        Scanner scan=new Scanner(System.in);
//        Charset charset = Charset.forName("UTF-8");
//        while(scan.hasNextLine()){
//            // 读取键盘输入
//            String line=scan.nextLine();
//            // 将键盘输入的内容输出到SocketChannel中
//            socketChannel.write(charset.encode(line));
//        }

        boolean isDone = false;
        while (!isDone) {
            int n = selector.select();
            if (n > 0) {
                for (SelectionKey key : selector.selectedKeys()) {
                    selector.selectedKeys().remove(key);
                    // ========================================================

                    if (key.isValid()) {
                        SocketChannel sc = (SocketChannel) key.channel();
                        if (key.isConnectable()) {
                            if (sc.finishConnect()) {
                                sc.register(selector, SelectionKey.OP_READ);
                                // 写数据
                                System.out.println("向服务端写数据！");
                                String message = "QUERY DATE!";
                                ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                                writeBuffer.put(message.getBytes("UTF-8"));
                                writeBuffer.flip();
                                sc.write(writeBuffer);
                            }
                        }

                        if (key.isReadable()) {
                            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                            int readLength = sc.read(readBuffer);
                            if (readLength > 0) {
                                readBuffer.flip();
                                byte[] bytes = new byte[readBuffer.remaining()];
                                readBuffer.get(bytes);
                                System.out.println("接收到服务端响应数据：" + new String(bytes, "UTF-8"));
                            }

                            //key.interestOps(SelectionKey.OP_READ);
                            isDone = true;
                            System.out.println("任务完毕");
                            key.cancel();
                            key.channel().close();
                        }
                    }

                    // ========================================================
                }
            }
        }

        selector.close();
        socketChannel.close();

    }

}
