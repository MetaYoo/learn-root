package com.kotall.learn.jms;

import com.lmax.disruptor.EventFactory;

/**
 * 描述：
 *
 * @author: zpwang
 * @time: 2019/9/6 14:53
 */
public class SeckillEventFactory implements EventFactory<SeckillEvent> {
    @Override
    public SeckillEvent newInstance() {
        return new SeckillEvent();
    }
}
