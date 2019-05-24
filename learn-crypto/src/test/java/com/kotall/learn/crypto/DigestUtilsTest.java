package com.kotall.learn.crypto;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.security.MessageDigest;

/**
 * desc:
 *
 * @author zpwang
 * @date 2019/5/23 19:26
 * @since 1.0.0
 */
public class DigestUtilsTest {

    @Test
    public void test1() throws Exception {
        DigestUtils digestUtils = null;
        MessageDigest messageDigest = DigestUtils.getDigest("AES/ECB/PKCS7Padding");
    }
}
