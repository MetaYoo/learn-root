package com.kotall.learn.map;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRU<K, V> extends LinkedHashMap {

    private int cacheSize;

    public LRU(int cacheSize) {
        super((int) Math.ceil(cacheSize / 0.75f) + 1, 0.75f, true);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return this.size() > cacheSize;
    }
}
