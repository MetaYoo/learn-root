package com.kotall.learn.io;

import java.util.Scanner;

/**
 * desc:
 *
 * @author zpwang
 * @date 2020/2/26 12:44
 * @since 1.0.0
 */
public class IOTest {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String msg = scanner.nextLine();
            System.out.println(msg);
        }

    }
}
