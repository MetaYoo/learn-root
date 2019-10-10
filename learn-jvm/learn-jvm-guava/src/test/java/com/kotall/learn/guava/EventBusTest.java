package com.kotall.learn.guava;

import com.google.common.eventbus.EventBus;
import org.junit.Test;

/**
 * desc:
 *
 * @author zpwang
 * @date 2019/10/10 14:46
 * @since 1.0.0
 */
public class EventBusTest {

    @Test
    public void eventBus() {
        EventBus eventBus = new EventBus();
        eventBus.register(new MsgListener());
        eventBus.post("hello");
        eventBus.post(123);
    }
}
