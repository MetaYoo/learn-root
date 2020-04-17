package com.kotall.learn;

import org.junit.Test;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

public class TreeMapTest {

    @Test
    public void test1() {
        TreeMap<String, Integer> treeMap = new TreeMap<>((o1, o2) -> -o1.compareTo(o2));
        treeMap.put("key_1", 1);
        treeMap.put("key_2", 2);
        treeMap.put("key_3", 3);
        Set<String> keys = treeMap.keySet();
        Iterator<String> iter = keys.iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            System.out.println(" " + key + ":" + treeMap.get(key));
        }
    }
}
