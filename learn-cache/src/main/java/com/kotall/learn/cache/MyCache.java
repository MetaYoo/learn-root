package com.kotall.learn.cache;

/**
 * desc:
 *
 * @author zpwang
 * @date 2019/3/27 15:14
 * @since 1.0.0
 */
public class MyCache extends LocalCache<String, String> {

    @Override
    String doLoadCacheData(String key) {
        System.out.println("读取数据");
        return "test";
    }
}
