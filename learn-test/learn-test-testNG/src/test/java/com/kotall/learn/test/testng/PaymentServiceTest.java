package com.kotall.learn.test.testng;

import mockit.Mock;
import mockit.MockUp;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author : zpwang
 * @version : 1.0.0
 * @date : 2018/3/16
 */
public class PaymentServiceTest {

    MockUp<PaymentService> proxyStub;

    @Test
    public void testPay() {
        proxyStub = new MockUp<PaymentService>() {
            @Mock
            public String pay(String orderId) {
                return "payed order: " + orderId;
            }
        };

        PaymentService mockInstance = proxyStub.getMockInstance();

        String rs = mockInstance.pay("20180316000001");
        Assert.assertEquals("payed order: 20180316000001", rs);
    }


    @Test
    public void testCount() {
        proxyStub = new MockUp<PaymentService>() {
            @Mock
            public int count(String orderId) {
                return 1000;
            }
        };

        PaymentService mockInstance = proxyStub.getMockInstance();

        int rs = mockInstance.count("20180316000001");
        Assert.assertEquals(1000, rs);
    }

}
