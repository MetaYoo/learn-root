package com.kotall.learn.jvm;

import java.util.ArrayList;
import java.util.List;

public class JvmTest1 {

    public static void main(String[] args) {

        System.getProperties().propertyNames();
        while (System.getProperties().propertyNames().hasMoreElements()) {

        }
        System.out.println();
        List<OOMObject> list = new ArrayList<>();
//        while (true) {
//            list.add(new OOMObject());
//        }

    }

    static class OOMObject {

    }
}
