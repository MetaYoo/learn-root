package com.kotall.learn.nio.demo2;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class Server {
    public static void main(String[] args) throws Exception {
        //new Thread(new Reactor()).start();
        new Reactor().run();

        //TimeUnit.SECONDS.sleep(1000);
    }
}
