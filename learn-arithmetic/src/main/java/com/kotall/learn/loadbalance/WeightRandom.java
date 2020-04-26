package com.kotall.learn.loadbalance;

import java.util.*;

/**
 * 完全随机
 */
public class WeightRandom {

    static Servers servers = new Servers();
    static Random random = new Random();

    public static void main(String[] args) {
        for (int i = 0; i < 15; i++) {
            System.out.println(go());
        }
    }

    public static String go() {
        List<String> ipList = new ArrayList<>();
        servers.map.forEach((k, v) -> {
            for (int i = 0; i < v.intValue(); i++) {
                ipList.add(k);
            }
        });

        int allWeight = ipList.size();
        int number = random.nextInt(allWeight);
        return ipList.get(number);
    }

    static class Servers {
        public Map<String, Integer> map = new HashMap<String, Integer>() {
            {
                put("192.168.1.1", 2);
                put("192.168.1.2", 7);
                put("192.168.1.3", 1);
            }
        };

    }

}
