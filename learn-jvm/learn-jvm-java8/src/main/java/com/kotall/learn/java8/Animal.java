package com.kotall.learn.java8;

/**
 * desc:
 *
 * @author zpwang
 * @date 2020/1/2 12:22
 * @since 1.0.0
 */
public interface Animal<T extends Object> {

    void eat(T food);
}
