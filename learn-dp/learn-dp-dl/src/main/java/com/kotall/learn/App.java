package com.kotall.learn;

import java.io.*;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) throws Exception {
        InputStream is = App.class.getClassLoader().getResourceAsStream("question.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));



        br.close();
        is.close();
    }
}
