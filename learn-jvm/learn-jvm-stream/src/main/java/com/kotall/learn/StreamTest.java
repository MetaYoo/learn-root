package com.kotall.learn;

import java.util.stream.IntStream;

public class StreamTest {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int sum2 = IntStream.of(nums).parallel().sum();
        System.out.println("结果为：" + sum2);
        System.out.println("================================");
        int[] sum3 = IntStream.of(nums)
                .filter(v -> v >= 2)
                .peek(v -> System.out.println("filtered value: " + v))
                .map(v -> v * v)
                .peek(v -> System.out.println("mapped value: " + v)).toArray()
        ;
        System.out.println(sum3[0]);
    }
}
