package com.kotall.learn.loadbalance;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 完全随机
 */
public class FullRandom {

    static Servers servers = new Servers();
    static Random random = new Random();

    public static String go() {
        int number = random.nextInt(servers.list.size());
        return servers.list.get(number);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 15; i++) {
            System.out.println(go());
        }
    }

    static class Servers {
        public List<String> list = new ArrayList<String>() {
            {
                add("192.168.1.1");
                add("192.168.1.2");
                add("192.168.1.3");
            }
        };

    }

}
