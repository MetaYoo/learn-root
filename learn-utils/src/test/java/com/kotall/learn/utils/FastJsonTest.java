package com.kotall.learn.utils;

import com.alibaba.fastjson.JSON;
import org.junit.Assert;
import org.junit.Test;

/**
 * desc:
 *
 * @author zpwang
 * @date 2019/3/27 8:51
 * @since 1.0.0
 */
public class FastJsonTest {

    @Test
    public void testSerialize() {
        LogDTO logDTO = new LogDTO(1, "test title");
        String jsonStr = JSON.toJSONString(logDTO, false);
        Assert.assertEquals("{\"c\":1,\"t\":\"test title\"}", jsonStr);
        LogDTO nLogDTO = JSON.parseObject(jsonStr, LogDTO.class);
        Assert.assertEquals(1, nLogDTO.getCount());
    }
}
