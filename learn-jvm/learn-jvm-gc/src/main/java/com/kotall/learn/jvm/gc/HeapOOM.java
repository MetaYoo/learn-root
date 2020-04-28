package com.kotall.learn.jvm.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xms100m -Xmx100m -XX:HeapDumpOnOutOfMemoryError -XX:+PrintGCDateStamps -XX:PrintGCDetails
 */
public class HeapOOM {
    byte[] b = new byte[1024 * 1024];

    public static void main(String[] args) throws InterruptedException {

        List<HeapOOM> all = new ArrayList<HeapOOM>();
        while (true) {
            all.add(new HeapOOM());
            Thread.sleep(20);
        }
    }
}
