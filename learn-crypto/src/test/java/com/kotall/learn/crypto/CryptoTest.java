package com.kotall.learn.crypto;

import org.junit.Test;

import java.io.*;

/**
 * 描述：
 *
 * @author: zpwang
 * @time: 2019/3/16 9:00
 */
public class CryptoTest {

    @Test
    public void DES() {
        String content = "hello,您好";
        String key = "sde@5f98H*^hsff%dfs$r344&df8543*er";
        System.out.println("content:" + content);
        String s1 = CryptoKit.encrypt(content, key, CryptoKit.KEY_ALGORITHM_DES, CryptoKit.CIPHER_ALGORITHM_DES, 56);
        System.out.println("DES encrypt s1:" + s1);
        System.out.println("DES decrypt s1:" + CryptoKit.decrypt(s1, key, CryptoKit.KEY_ALGORITHM_DES, CryptoKit.CIPHER_ALGORITHM_DES, 56));
    }

    @Test
    public void tripDES() {
        String content = "hello,您好";
        String key = "sde@5f98H*^hsff%dfs$r344&df8543*er";
        System.out.println("content:" + content);
        String s1 = CryptoKit.encrypt(content, key, CryptoKit.KEY_ALGORITHM_3DES, CryptoKit.CIPHER_ALGORITHM_3DES, 168);
        System.out.println("3DES encrypt s1:" + s1);
        System.out.println("3DES decrypt s1:" + CryptoKit.decrypt(s1, key, CryptoKit.KEY_ALGORITHM_3DES, CryptoKit.CIPHER_ALGORITHM_3DES, 168));
    }

    @Test
    public void AES() {
        String content = "hello,您好";
        String key = "sde@5f98H*^hsff%dfs$r344&df8543*er";
        System.out.println("content:" + content);
        String s1 = CryptoKit.encrypt(content, key, CryptoKit.KEY_ALGORITHM_AES, CryptoKit.CIPHER_ALGORITHM_AES, 128);
        System.out.println("AES encrypt s1:" + s1);
        System.out.println("AES decrypt s1:" + CryptoKit.decrypt(s1, key, CryptoKit.KEY_ALGORITHM_AES, CryptoKit.CIPHER_ALGORITHM_AES, 128));
    }

    @Test
    public void myCryptoEncoder() throws Exception {
        String myKey = "";
        BufferedReader bufferedReader = new BufferedReader(new FileReader("D:/usr/home/data/note.txt"));
        StringBuilder stringBuilder = new StringBuilder(1024);
        String tmp = bufferedReader.readLine();
        while (tmp != null) {
            stringBuilder.append(tmp + "\r\n");
            tmp = bufferedReader.readLine();
        }

        String cryptoStr = CryptoKit.encrypt(stringBuilder.toString(), myKey, CryptoKit.KEY_ALGORITHM_AES, CryptoKit.CIPHER_ALGORITHM_AES, 128);

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D:/usr/home/data/note.pwd"));
        bufferedWriter.write(cryptoStr);


        bufferedReader.close();
        bufferedWriter.close();
    }


    @Test
    public void myCryptoDecoder() throws Exception {
        String myKey = "";
        BufferedReader bufferedReader = new BufferedReader(new FileReader("D:/usr/home/data/note.pwd"));
        StringBuilder stringBuilder = new StringBuilder(1024);
        String tmp = bufferedReader.readLine();
        while (tmp != null) {
            stringBuilder.append(tmp);
            tmp = bufferedReader.readLine();
        }

        String decodeStr = CryptoKit.decrypt(stringBuilder.toString(), myKey, CryptoKit.KEY_ALGORITHM_AES, CryptoKit.CIPHER_ALGORITHM_AES, 128);

        System.out.printf(decodeStr);

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D:/usr/home/data/note.txt"));
        bufferedWriter.write(decodeStr);


        bufferedReader.close();
        bufferedWriter.close();
    }

}
