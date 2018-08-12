package com.kotall.learn.dp;

import java.io.IOException;
import java.net.InetAddress;
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

    public final Selector selector;
    public final ServerSocketChannel serverSocketChannel;

    public Reactor(int port) throws IOException{
        selector=Selector.open();
        serverSocketChannel=ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress=new InetSocketAddress(InetAddress.getLocalHost(),port);
        serverSocketChannel.socket().bind(inetSocketAddress);
        serverSocketChannel.configureBlocking(false);

        //向selector注册该channel
        SelectionKey selectionKey=serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //利用selectionKey的attache功能绑定Acceptor 如果有事情，触发Acceptor
        selectionKey.attach(new Acceptor(this));
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()){
                selector.select();
                Set<SelectionKey> selectionKeys= selector.selectedKeys();
                Iterator<SelectionKey> it=selectionKeys.iterator();
                //Selector如果发现channel有OP_ACCEPT或READ事件发生，下列遍历就会进行。
                while(it.hasNext()){
                    //来一个事件 第一次触发一个accepter线程
                    //以后触发SocketReadHandler
                    SelectionKey selectionKey=it.next();
                    dispatch(selectionKey);
                    selectionKeys.clear();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 运行Acceptor或SocketReadHandler
     * @param key
     */
    void dispatch(SelectionKey key) {
        Runnable r = (Runnable)(key.attachment());
        if (r != null){
            r.run();
        }
    }

}