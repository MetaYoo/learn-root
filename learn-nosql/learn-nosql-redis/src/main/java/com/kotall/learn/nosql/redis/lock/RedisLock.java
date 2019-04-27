package com.kotall.learn.nosql.redis.lock;

import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * desc:
 * redis 锁实现
 *
 * @author zpwang
 * @create 2019/3/22 10:09
 * @since 1.0.0
 */
@Component
public class RedisLock {

    /**
     * 锁前缀
     */
    private static final String LOCK_PREFIX = "lock_";

    private static final Long RELEASE_SUCCESS = 1L;


    @Resource
    private RedisTemplate redisTemplate;
    private DefaultRedisScript<Long> tryLockScript;
    private DefaultRedisScript<Long> releaseLockScript;

    @PostConstruct
    public void init() {
        tryLockScript = new DefaultRedisScript<>();
        tryLockScript.setResultType(Long.class);
        tryLockScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("script/redis_try_lock.lua")));

        releaseLockScript = new DefaultRedisScript<>();
        releaseLockScript.setResultType(Long.class);
        releaseLockScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("script/redis_release_lock.lua")));
    }

    /**
     * 尝试获取分布式锁
     * redis 3+以上版本有原子操作命令，设置值且有过期时间，不需要lua脚本
     *
     * @param lockKey    锁
     * @param requestId  请求标识
     * @param expireTime 超时时间 单位：毫秒
     * @return 是否获取成功
     */
    public boolean tryLock(String lockKey, String requestId, Long expireTime) {
        String lockName = LOCK_PREFIX + lockKey;
        return redisTemplate.opsForValue().setIfAbsent(lockName, requestId, expireTime, TimeUnit.MILLISECONDS);
    }

    /**
     * 释放分布式锁
     *
     * @param lockKey   锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public boolean releaseLock(String lockKey, String requestId) {
//        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
//        RedisScript<Long> redisScript = new DefaultRedisScript<>(script, Long.class);
        String lockName = LOCK_PREFIX + lockKey;
        Object result = redisTemplate.execute(releaseLockScript, Collections.singletonList(lockName), requestId);
        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }
}
