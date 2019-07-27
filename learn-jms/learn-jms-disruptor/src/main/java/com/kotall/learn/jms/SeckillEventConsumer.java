package com.kotall.learn.jms;

import com.lmax.disruptor.EventHandler;

/**
 * 描述：
 *
 * @author: zpwang
 * @time: 2019/9/6 14:53
 */
public class SeckillEventConsumer implements EventHandler<SeckillEvent> {
    //业务处理、这里是无法注入的，需要手动获取，见源码
    //private ISeckillService seckillService = (ISeckillService) SpringUtil.getBean("seckillService");

    @Override
    public void onEvent(SeckillEvent seckillEvent, long seq, boolean bool) throws Exception {
        //seckillService.startSeckil(seckillEvent.getSeckillId(), seckillEvent.getUserId());
    }
}
