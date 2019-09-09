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
        Assert.assertEquals(1, nLogDTO.getCount().intValue());
    }

    @Test
    public void testStr() {
        String str = "{\"adCategoryId\":5187,\"advertiserId\":205036,\"checkRemark\":\"待审核\",\"checkStatus\":1,\"creativeHeight\":400,\"creativeId\":2000042,\"creativeTypeId\":2,\"creativeWidth\":600,\"customerId\":1200001,\"dspId\":666,\"elements\":{\"appDesc\":\"test22\",\"appSize\":0.0,\"downloadTypeId\":1,\"infoFlow\":[{\"desc\":\"test22\",\"feedTypeId\":9,\"images\":[{\"height\":400,\"url\":\"https://img.zcdsp.com/creative/material/8f3916d50bc93d07b43e14466f6fd13e.jpg\",\"width\":600}],\"title\":\"test\"}],\"landingpageUrl\":\"http://www.zcyidong.cn:4100/template/3\",\"logo\":{\"height\":400,\"url\":\"https://img.zcdsp.com/creative/logo/1561968517472.jpg\",\"width\":600},\"thirdCategory\":{}},\"expiryDate\":\"2019-10-31 11:04:33\",\"height\":720,\"mediaUserIdList\":[466],\"platformMediaId\":11,\"uploadCreativeInfo\":\"{\\\"appSize\\\":0.0,\\\"desc\\\":\\\"test22\\\",\\\"downloadTypeId\\\":1,\\\"feedTypeId\\\":9,\\\"images\\\":[{\\\"height\\\":296,\\\"url\\\":\\\"https://img.zcdsp.com/creative/material/8f3916d50bc93d07b43e14466f6fd13e.jpg\\\",\\\"width\\\":600}],\\\"landingpageUrl\\\":\\\"http://www.zcyidong.cn:4100/template/3\\\",\\\"logo\\\":{\\\"height\\\":400,\\\"url\\\":\\\"https://img.zcdsp.com/creative/logo/1561968517472.jpg\\\",\\\"width\\\":600},\\\"title\\\":\\\"test\\\"}\",\"width\":600}";
        System.out.println(str.replaceAll(" ", "").replaceAll("\n", ""));
    }


}
