package com.kotall.learn.utils;

import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;

public class HuToolTest {
    public static void main(String[] args) {
        System.out.println(NetUtil.ipv4ToLong(NetUtil.getLocalhostStr()));
        IdUtil.createSnowflake(2, 3);
    }
}
