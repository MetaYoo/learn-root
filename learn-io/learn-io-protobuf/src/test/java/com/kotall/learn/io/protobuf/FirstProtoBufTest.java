package com.kotall.learn.io.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.Test;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class FirstProtoBufTest {

    @Test
    public void test1() throws InvalidProtocolBufferException {

        FirstProtobuf.testBuf.Builder builder = FirstProtobuf.testBuf.newBuilder();
        builder.setID(1);
        builder.setUrl("www.baidu.com");
        builder.addName("aaa");
        builder.addName("bbb");
        builder.addName("ccc");

        FirstProtobuf.testBuf info = builder.build();
        byte[] result = info.toByteArray();
        System.out.println("google protobuf size= " + result.length);

        FirstProtobuf.testBuf testBuf = FirstProtobuf.testBuf.parseFrom(result);
        System.out.println(testBuf);
    }
}
