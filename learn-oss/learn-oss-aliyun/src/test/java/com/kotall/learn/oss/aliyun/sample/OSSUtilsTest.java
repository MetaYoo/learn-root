package com.kotall.learn.oss.aliyun.sample;

import com.aliyun.oss.internal.OSSUtils;
import org.junit.Test;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class OSSUtilsTest {

    @Test
    public void validateBucketName() {
        OSSUtils.ensureBucketNameValid("oss-cn-hangzhou.aliyuncs.com");

    }
}
