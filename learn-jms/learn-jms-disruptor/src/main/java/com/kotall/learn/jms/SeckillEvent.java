package com.kotall.learn.jms;

import java.io.Serializable;

/**
 * 描述：
 *
 * @author: zpwang
 * @time: 2019/9/6 14:52
 */
public class SeckillEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    private long seckillId;
    private long userId;

    public SeckillEvent(){

    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
