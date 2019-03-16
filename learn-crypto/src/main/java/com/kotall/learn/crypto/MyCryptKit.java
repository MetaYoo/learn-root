package com.kotall.learn.crypto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * 描述：
 *
 * @author: zpwang
 * @time: 2019/3/16 9:14
 */
public class MyCryptKit {

    public static void main(String[] args) throws Exception {

        MyCryptKit myCryptKit = new MyCryptKit();
        // 加密
        myCryptKit.encode(args[0]);
        // 解密
        //myCryptKit.decode(args[0]);
    }


    public void encode(String myKey) throws Exception {

        String str = readFile("D:/usr/home/data/note.txt");
        System.out.printf(str);
        String cryptoStr = CryptoKit.encrypt(str, myKey, CryptoKit.KEY_ALGORITHM_AES, CryptoKit.CIPHER_ALGORITHM_AES, 128);

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D:/usr/home/data/note.pwd"));
        bufferedWriter.write(cryptoStr);
        bufferedWriter.close();
    }


    public void decode(String myKey) throws Exception {
        String str = readFile("D:/usr/home/data/note.txt");
        System.out.printf(str);
        String decodeStr = CryptoKit.decrypt(str, myKey, CryptoKit.KEY_ALGORITHM_AES, CryptoKit.CIPHER_ALGORITHM_AES, 128);

        System.out.printf(decodeStr);
    }

    public String readFile(String file) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        StringBuilder stringBuilder = new StringBuilder(1024);
        String tmp = bufferedReader.readLine();
        while (tmp != null) {
            stringBuilder.append(tmp);
            tmp = bufferedReader.readLine();
        }
        bufferedReader.close();
        return stringBuilder.toString();
    }
}
