package com.kotall.learn.jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author wzp
 * @date 2020-03-08
 */
public class JOLTest {
    public static void main(String[] args) {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}
