package com.kotall.learn;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelReadTest {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("D:/1.txt");
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        FileChannel fileChannel = fileInputStream.getChannel();
        while (true) {
            int read = fileChannel.read(byteBuffer);
            if (read == -1) {
                break;
            }
            System.out.println(new String(byteBuffer.array()));
        }

        fileChannel.close();
        fileInputStream.close();
    }
}
