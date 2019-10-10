package com.kotall.learn.guava;

import com.google.common.eventbus.Subscribe;

/**
 * desc:
 *
 * @author zpwang
 * @date 2019/10/10 14:46
 * @since 1.0.0
 */
public class MsgListener {

    @Subscribe
    public void strEvent(String event) {
        System.out.println("str ====>" + event);
    }

    @Subscribe
    public void intEvent(Integer event) {
        System.out.println("int ====>" + event);
    }
}
