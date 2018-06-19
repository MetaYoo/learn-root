package com.kotall.learn.io;

import org.junit.Test;

import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class ZipFileTest {

    @Test
    public void testWriteZipFile() throws Exception {
        FileOutputStream fos = new FileOutputStream("D:/test/test.zip");
        ZipOutputStream zos = new ZipOutputStream(fos);
        for (int i=0; i < 5; i++) {
            zos.putNextEntry(new ZipEntry("com/kotall/test" + i + ".txt"));
            String content = "hello world";
            byte[] tmp = content.getBytes();
            zos.write(tmp, 0, tmp.length);
            zos.closeEntry();
        }

        zos.close();
        fos.close();

    }
}
