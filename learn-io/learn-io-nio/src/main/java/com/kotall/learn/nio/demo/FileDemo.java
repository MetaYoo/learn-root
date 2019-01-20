package com.kotall.learn.nio.demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * NIO
 * @author zpwang
 * @version 1.0.0
 */
public class FileDemo {

    public static void main(String[] args) throws Exception {
        //readNio();
        writeNio();
    }

    private static void readNio() throws IOException {
        FileInputStream fis = new FileInputStream("D:/note.txt");
        FileChannel fileChannel = fis.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        System.out.println("缓冲区限制是：" + byteBuffer.limit() + ", 容量是：" + byteBuffer.capacity() + " ,位置是：" + byteBuffer.position());

        int len;
        while ((len = fileChannel.read(byteBuffer)) != -1) {
            byteBuffer.flip();
            byte[] bytes = byteBuffer.array();
            System.out.write(bytes, 0, len);
            System.out.println("缓冲区限制是：" + byteBuffer.limit() + ", 容量是：" + byteBuffer.capacity() + " ,位置是：" + byteBuffer.position());
        }
        fileChannel.close();
        fis.close();
    }

    private static void writeNio() throws IOException {
        FileOutputStream fis = new FileOutputStream("D:/testNio.txt");
        FileChannel fileChannel = fis.getChannel();

        ByteBuffer byteBuffer = Charset.forName("UTF-8").encode("你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！你好你好你好你好你好你好你好你好大家好才是真的好！");
        System.out.println("缓冲区限制是：" + byteBuffer.limit() + ", 容量是：" + byteBuffer.capacity() + " ,位置是：" + byteBuffer.position());

        int len;
        while ((len = fileChannel.write(byteBuffer)) != 0) {
            System.out.println("写入长度: " + len);
            System.out.println("缓冲区限制是：" + byteBuffer.limit() + ", 容量是：" + byteBuffer.capacity() + " ,位置是：" + byteBuffer.position());
        }
        fileChannel.close();
        fis.close();
    }
}
