package com.kotall.learn;

import org.junit.Test;

import java.io.*;

/**
 * 描述：
 *
 * @author: zpwang
 * @time: 2019/4/8 21:34
 */
public class FileTest {

    @Test
    public void testNio() throws Exception {
        long startTime = System.currentTimeMillis();
        // 将后面的内容写入到新文件
        FileOutputStream fos = new FileOutputStream("D:/bigfile.txt");
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));

        for (int i = 0; i < 100000000; i++) {
            writer.write(i + ", the," + i + ",row," + System.currentTimeMillis() + "|" + System.currentTimeMillis() + System.currentTimeMillis() + "|" + System.currentTimeMillis() + System.currentTimeMillis() + "|" + System.currentTimeMillis() + System.currentTimeMillis() + "|" + System.currentTimeMillis() + System.currentTimeMillis() + "|" + System.currentTimeMillis() + "\n");
            writer.flush();
        }

        fos.close();
        writer.close();
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }

    @Test
    public void testReadBigFile() throws Exception {
        long startTime = System.currentTimeMillis();
        FileInputStream fis = new FileInputStream("D:/bigfile.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
        String tmp;
        while ((tmp = reader.readLine()) != null) {
            // todo
            String[] arr = tmp.split(",");
        }
        reader.close();
        fis.close();
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }

    @Test
    public void testReadBigFileWithBuf() throws Exception {

    }


}
