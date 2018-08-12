package com.kotall.learn.io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class Server implements Runnable {

    private static final int PORT = 9999;
    private static final int MAX_INPUT = 1024;

    private ExecutorService executor = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors(),
            50,
            120L,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10000));

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(PORT);
            while (!Thread.interrupted())
                new Thread(new Handler(ss.accept())).start(); //创建新线程来handle
            // or, single-threaded, or a thread pool
            // executor.submit(new Handler(ss.accept()));
        } catch (IOException ex) { /* ... */ }
    }

    /**
     * blocking io
     */
    static class Handler implements Runnable {
        final Socket socket;
        Handler(Socket s) { socket = s; }
        public void run() {
            try {
                byte[] input = new byte[MAX_INPUT];
                socket.getInputStream().read(input);
                byte[] output = process(input);
                socket.getOutputStream().write(output);
            } catch (IOException ex) { /* ... */ }
        }

        private byte[] process(byte[] cmd) {
            System.out.println(new String(cmd));
            return "Ok".getBytes();
        }
    }

    public static void main(String[] args) {
        new Thread(new Server()).start();
    }
}
