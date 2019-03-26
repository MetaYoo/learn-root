package com.kotall.learn.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * desc:
 *
 * @author zpwang
 * @date 2019/3/26 13:09
 * @since 1.0.0
 */
public class StreamTest {

//    int counter;
//    private void wasCalled() {
//        counter++;
//    }

    @Test
    public void testStream() {
        List<String> list = Arrays.asList("abc1", "abc2", "abc3");
        Stream<String> stream = list.stream().filter(element -> {
            //wasCalled();
            System.out.println("called");
            return element.contains("2");
        });
        System.out.println(stream.count());

        Integer[] sixNums = {1, 2, 3, 4, 5, 6};
        Integer[] evens = Stream.of(sixNums).filter(n -> n % 2 == 0).toArray(Integer[]::new);
        Stream.of(evens).forEach(System.out::println);
    }

    @Test
    public void testStream2() {
        List<String> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        list1.add("c");
        list1.add("d");
        list1.add("e");
        list1.add("f");
        List<String> list2 = new ArrayList<>();
        list1.add("e");
        list1.add("e");


    }
}
