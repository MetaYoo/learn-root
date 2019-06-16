package com.kotall.learn.threadlocal;

import org.junit.Test;

public class ThreadLocalTest {

    @Test
    public void testThread() {

        Thread thread = Thread.currentThread();
        
    }

    @Test
    public void testThreadLocal() {
        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.set("tttt");
    }

}
