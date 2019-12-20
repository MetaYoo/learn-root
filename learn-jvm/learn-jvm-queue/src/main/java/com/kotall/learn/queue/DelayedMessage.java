package com.kotall.learn.queue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * desc:
 *
 * @author zpwang
 * @date 2019/12/20 10:00
 * @since 1.0.0
 */

public class DelayedMessage implements Delayed {

    private int id;

    /**
     * 消息内容
     */
    private String body;

    /**
     * 延迟时长，这个是必须的属性因为要按照这个判断延时时长
     */
    private long executeTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(long executeTime) {
        this.executeTime = executeTime;
    }

    public DelayedMessage(int id, String body, long delayTime) {
        this.id = id;
        this.body = body;
        this.executeTime = TimeUnit.NANOSECONDS.convert(delayTime, TimeUnit.MILLISECONDS) + System.nanoTime();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.executeTime - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed delayed) {
        DelayedMessage msg = (DelayedMessage) delayed;
        return Integer.valueOf(this.id) > Integer.valueOf(msg.id) ? 1
                : (Integer.valueOf(this.id) < Integer.valueOf(msg.id) ? -1 : 0);
    }
}
