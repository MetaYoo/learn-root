package com.kotall.learn;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        long t1 = TimeUnit.NANOSECONDS.convert(5000, TimeUnit.MILLISECONDS) + System.nanoTime();
    }
}
