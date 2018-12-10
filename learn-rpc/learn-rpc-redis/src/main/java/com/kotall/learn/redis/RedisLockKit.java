package com.kotall.learn.redis;

import redis.clients.jedis.Jedis;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class RedisLockKit {

    public static void main(String[] args) {

    }

    /**
     * 用redis的 setnx方法
     * @param key
     * @return
     */
    public static boolean lockBySetnx(String key) {
        Jedis jedis = new Jedis("127.0.0.1", 6379, 30);
        jedis.connect();

        Long r1 = jedis.setnx("k1", "123456");

        if (1 == r1) {
            jedis.expire("k1", 10);
            return true;
        }

        jedis.disconnect();
        return false;
    }

    /**
     * 1. setnx
     * 2. get
     * 3. getset
     *
     * @param key
     * @return
     */
    public static boolean lockByGetSet(String key, Long expireTime) {
        Jedis jedis = new Jedis("127.0.0.1", 6379, 30);
        jedis.connect();

        Long value = System.currentTimeMillis() + expireTime;
        Long r1 = jedis.setnx("k1", String.valueOf(value));

        if (1 == r1) {
            // 已经成功获取了锁
            return true;
        }

        // 没有获取到锁，可能锁正在被别人使用，或者锁已经超时了
        // 检查redis中的value
        long oldExpireTime = Long.parseLong(jedis.get("k1"));
        if (oldExpireTime < System.currentTimeMillis()) {
            // 锁已超时，可以重新获取锁
            long newExpireTime = System.currentTimeMillis() + expireTime;
            long currentExpireTime = Long.parseLong(jedis.getSet("k1", String.valueOf(newExpireTime)));
            if (oldExpireTime == currentExpireTime ) {
                return true;
            }
        }

        jedis.disconnect();
        return false;
    }

}
