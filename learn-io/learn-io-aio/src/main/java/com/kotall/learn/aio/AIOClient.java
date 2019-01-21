package com.kotall.learn.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class AIOClient implements Runnable {

    private AsynchronousSocketChannel client;

    public AIOClient() {
        try {
            client = AsynchronousSocketChannel.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connect() {
        try {
            client.connect(new InetSocketAddress(20880)).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void write(String request) {
        try {
            client.write(ByteBuffer.wrap(request.getBytes())).get();
            read();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void read() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            client.read(buffer).get();
            buffer.flip();
            byte[] bytes = new byte[buffer.remaining()];
            buffer.get(bytes);
            System.out.println(new String(bytes));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void run() {
        while (true) {

        }
    }

    public static void main(String[] args) throws InterruptedException {
        AIOClient c1 = new AIOClient();
        AIOClient c2 = new AIOClient();
        AIOClient c3 = new AIOClient();


        new Thread(c1,"c1").start();
        new Thread(c2,"c2").start();
        new Thread(c3,"c3").start();

        TimeUnit.SECONDS.sleep(3);

        c1.write("c1");
        c2.write("c2");
        c3.write("c3");

        TimeUnit.SECONDS.sleep(100);

    }
}
