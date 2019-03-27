package com.kotall.learn.cache;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * desc:
 *
 * @author zpwang
 * @date 2019/3/27 15:08
 * @since 1.0.0
 */
public class GuavaCacheTest {

    @Test
    public void demo1() throws Exception {
        MyCache myCache = new MyCache();
        //myCache.put("k1", "v1");
        String v1 = myCache.get("k1");
        System.out.println(v1);

        v1 = myCache.get("k1");
        System.out.println(v1);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    int j = new Random().nextInt(60);
                    TimeUnit.SECONDS.sleep(5 + j);

                    String r = myCache.get("k1");
                    System.out.println(Thread.currentThread().getName() + ": " + r);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }

        TimeUnit.SECONDS.sleep(50);
    }
}
