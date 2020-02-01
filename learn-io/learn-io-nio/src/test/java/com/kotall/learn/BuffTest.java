package com.kotall.learn;

import java.nio.IntBuffer;

public class BuffTest {
    public static void main(String[] args) {
        IntBuffer buff = IntBuffer.allocate(1024);
        for (int i = 0; i < 100; i++) {
            buff.put(i);
        }
        buff.flip();
        while (buff.hasRemaining()) {
            System.out.println(buff.get());
        }
    }
}
