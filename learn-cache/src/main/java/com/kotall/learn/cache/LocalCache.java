package com.kotall.learn.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * desc:
 *
 * @author zpwang
 * @date 2019/3/27 15:09
 * @since 1.0.0
 */
@Slf4j
public abstract class LocalCache<K, V> {

    private LoadingCache<K, V> cache;

    public V get(K key) throws ExecutionException {
        return Objects.nonNull(key) ? getCache().get(key) : null;
    }

    public void put(K key, V value) {
        if (Objects.nonNull(key) && value != null) {
            getCache().put(key, value);
        }
    }

    public void remove(K key) {
        if (Objects.nonNull(key)) {
            getCache().invalidate(key);
        }
    }

    public void remove(List<K> keys) {
        if (keys != null && keys.size() > 0) {
            getCache().invalidateAll(keys);
        }
    }

    /**
     * 获取guava缓存对象
     *
     * @return
     */
    public LoadingCache<K, V> getCache() {
        if (this.cache == null) {
            synchronized (this) {
                if (this.cache == null) {
                    this.cache = CacheBuilder.newBuilder()
                            .initialCapacity(1000)
                            .maximumSize(10000)
                            .refreshAfterWrite(2, TimeUnit.SECONDS)
                            //.expireAfterWrite(2, TimeUnit.SECONDS)
                            /** 异步刷新、如果刷新失败则还是会使用旧值 */
                            .build(new CacheLoader<K, V>() {
                                @Override
                                public V load(K key) throws Exception {
                                    System.out.println("===================load 缓存数据");
                                    return doLoadCacheData(key);
                                }

                                @Override
                                public ListenableFuture<V> reload(K key, V oldValue) throws Exception {
                                    System.out.println("===================reload 加载缓存");
                                    ListeningExecutorService listeningExecutorService = MoreExecutors.newDirectExecutorService();
                                    ListenableFuture<V> future = listeningExecutorService.submit(() -> doLoadCacheData(key));
                                    return future;
                                }
                            });
                }
            }
        }
        return this.cache;
    }

    /**
     * @param key key
     * @return value
     */
    abstract V doLoadCacheData(K key);
}
