package com.kotall.learn;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 描述：
 *
 * @author: zpwang
 * @time: 2019/4/2 21:45
 */
public class BigFileReader {
    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("/app/log.txt");
        ByteBuffer buffer = ByteBuffer.allocate(10);
        FileChannel channel = fis.getChannel();
        int count;
        StringBuilder pre = new StringBuilder();
        boolean findZ = false;
        while ((count = channel.read(buffer)) > 0) {
            byte[] tmp = new byte[count];
            buffer.flip();
            buffer.get(tmp);
            buffer.clear();
            String str = new String(tmp);
            if (!findZ) {
                if (!str.contains("z=[")) {
                    pre.append(str);
                } else {
                    pre.append(str.substring(0, str.indexOf("z=[")));
                    findZ = true;
                }
            }
        }
        System.out.println(pre.toString());

        channel.close();
        fis.close();
    }
}
