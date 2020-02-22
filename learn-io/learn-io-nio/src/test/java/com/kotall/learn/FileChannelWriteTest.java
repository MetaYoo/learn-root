package com.kotall.learn;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelWriteTest {
    public static void main(String[] args) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream("D:/2.txt");
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        FileChannel fileChannel = fileOutputStream.getChannel();
        byteBuffer.putChar('中');
        byteBuffer.flip();
        fileChannel.write(byteBuffer);
        fileChannel.close();
        fileOutputStream.close();
    }
}
