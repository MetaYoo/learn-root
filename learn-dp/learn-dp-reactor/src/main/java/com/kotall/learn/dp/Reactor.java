package com.kotall.learn.dp;

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
    final ServerSocketChannel serverSocket;

    Reactor(int port) throws IOException { //Reactor初始化
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(port));
        serverSocket.configureBlocking(false); //非阻塞
        SelectionKey sk = serverSocket.register(selector, SelectionKey.OP_ACCEPT); //分步处理,第一步,接收accept事件
        sk.attach(new Acceptor()); //attach callback object, Acceptor
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                selector.select();
                Set selected = selector.selectedKeys();
                Iterator it = selected.iterator();
                while (it.hasNext())
                    dispatch((SelectionKey)(it.next())); //Reactor负责dispatch收到的事件
                selected.clear();
            }
        } catch (IOException ex) { /* ... */ }
    }

    void dispatch(SelectionKey k) {
        Runnable r = (Runnable)(k.attachment()); //调用之前注册的callback对象
        if (r != null)
            r.run();
    }

    class Acceptor implements Runnable { // inner
        public void run() {
            try {
                SocketChannel socket = serverSocket.accept();
                if (socket != null)
                    new Handler(selector, socket);
            }
            catch(IOException ex) { /* ... */ }
        }
    }
}

final class Handler implements Runnable {
    final SocketChannel socket;
    final SelectionKey sk;
    final int MAXIN = 1024;
    final int MAXOUT = 1024;
    ByteBuffer input = ByteBuffer.allocate(MAXIN);
    ByteBuffer output = ByteBuffer.allocate(MAXOUT);
    static final int READING = 0, SENDING = 1;
    int state = READING;

    Handler(Selector sel, SocketChannel socket) throws IOException {
        this.socket = socket;
        socket.configureBlocking(false);
        // Optionally try first read now
        sk = this.socket.register(sel, 0);
        sk.attach(this); //将Handler作为callback对象
        sk.interestOps(SelectionKey.OP_READ); //第二步,接收Read事件
        sel.wakeup();
    }
    boolean inputIsComplete() {
        /* ... */
        return true;
    }
    boolean outputIsComplete() {
        /* ... */
        return true;
    }

    void process() { /* ... */ }

    @Override
    public void run() {
        try {
            if (state == READING) read();
            else if (state == SENDING) send();
        } catch (IOException ex) { /* ... */ }
    }

    void read() throws IOException {
        socket.read(input);
        if (inputIsComplete()) {
            process();
            state = SENDING;
            // Normally also do first write now
            sk.interestOps(SelectionKey.OP_WRITE); //第三步,接收write事件
        }
    }

    void send() throws IOException {
        socket.write(output);
        if (outputIsComplete()) sk.cancel(); //write完就结束了, 关闭select key
    }
}

// 上面 的实现用Handler来同时处理Read和Write事件, 所以里面出现状态判断
// 我们可以用State-Object pattern来更优雅的实现
final class Handler1 implements Runnable { // ...
    final SocketChannel socket;
    final SelectionKey sk;
    final int MAXIN = 1024;
    final int MAXOUT = 1024;
    ByteBuffer input = ByteBuffer.allocate(MAXIN);
    ByteBuffer output = ByteBuffer.allocate(MAXOUT);

    Handler1(Selector sel, SocketChannel socket) throws IOException {
        this.socket = socket;
        socket.configureBlocking(false);
        // Optionally try first read now
        sk = this.socket.register(sel, 0);
        sk.attach(this); //将Handler作为callback对象
        sk.interestOps(SelectionKey.OP_READ); //第二步,接收Read事件
        sel.wakeup();
    }

    boolean inputIsComplete() {
        /* ... */
        return true;
    }
    boolean outputIsComplete() {
        /* ... */
        return true;
    }

    void process() { /* ... */ }

    @Override
    public void run() { // initial state is reader
        try {
            socket.read(input);
            if (inputIsComplete()) {
                process();
                sk.attach(new Sender());  //状态迁移, Read后变成write, 用Sender作为新的callback对象
                sk.interestOps(SelectionKey.OP_WRITE);
                sk.selector().wakeup();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    class Sender implements Runnable {

        @Override
        public void run() { // ...
            try {
                socket.write(output);
                if (outputIsComplete()) sk.cancel();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}