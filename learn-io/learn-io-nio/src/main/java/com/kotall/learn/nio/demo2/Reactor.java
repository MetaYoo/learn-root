package com.kotall.learn.nio.demo2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class Reactor implements Runnable {

    final Selector selector;
    final ServerSocketChannel ssc;

    public Reactor() throws IOException {
        selector = Selector.open();
        ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress(20880));
        SelectionKey sk = ssc.register(selector, SelectionKey.OP_ACCEPT);
        sk.attach(new Acceptor());
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeys.iterator();
                while (it.hasNext()) {
                    dispatch(it.next());
                }
                selectionKeys.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dispatch(SelectionKey key) {
        Runnable runnable = (Runnable) key.attachment();
        if (null != runnable) {
            runnable.run();
        }
    }


    class Acceptor implements Runnable {
        @Override
        public void run() {
            try {
                SocketChannel sc = ssc.accept();
                if (null != sc) {
                    new Handler(selector, sc);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    class Handler implements Runnable {
        final SelectionKey key;
        final SocketChannel socket;
        ByteBuffer input = ByteBuffer.allocate(1024);
        ByteBuffer output = ByteBuffer.allocate(1024);
        static final int READING = 0, SENDING = 1;
        int state = READING;

        boolean isInputComplete = false;
        boolean isOutputComplete = false;

        public Handler(Selector selector, SocketChannel socketChannel) throws IOException {
            this.socket = socketChannel;
            socket.configureBlocking(false);
            key = socket.register(selector, 0);
            key.attach(this);
            key.interestOps(SelectionKey.OP_READ);
            selector.wakeup();
        }

        @Override
        public void run() {
            try {
                if (state == READING) {
                    read();
                } else if (state == SENDING) {
                    send();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        boolean inputIsComplete() {
            return input.flip().hasRemaining();
        }

        boolean outputIsComplete() {
            return output.flip().hasRemaining();
        }

        void process() {
            // TODO
            // 处理数据
            // 往缓冲区放数据
            byte[] bytes = new byte[input.remaining()];
            input.get(bytes);

            System.out.println("读取到的数据：" + new String(bytes));
            output.put("I am from SUN !".getBytes());
        }

        void read() throws IOException {
            socket.read(input);
            if (inputIsComplete()) {
                process();
                state = SENDING;
                key.interestOps(SelectionKey.OP_WRITE);
            }

        }

        void send() throws IOException {
            socket.write(output);
            if (outputIsComplete()) {
                key.cancel();
            }

        }
    }

}


