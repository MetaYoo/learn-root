package com.kotall.learn.crypto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * 描述：
 * 对称加解密工具类
 *
 * @author: zpwang
 * @time: 2019/3/16 8:35
 */
public class CryptoKit {

    private static final Logger logger = LoggerFactory.getLogger(CryptoKit.class);

    public static final String KEY_ALGORITHM_DES = "DES";
    public static final String CIPHER_ALGORITHM_DES = "DES/ECB/PKCS5Padding";

    public static final String KEY_ALGORITHM_3DES = "DESede";
    public static final String CIPHER_ALGORITHM_3DES = "DESede/ECB/PKCS5Padding";

    public static final String KEY_ALGORITHM_AES = "AES";
    public static final String CIPHER_ALGORITHM_AES = "AES/ECB/PKCS5Padding";

    /**
     * DES 加密操作
     *
     * @param content         待加密内容
     * @param key             加密密钥
     * @param keyAlgorithm    加密算法Key
     * @param cipherAlgorithm 加密算法
     * @param keySize         秘钥长度
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content, String key, String keyAlgorithm, String cipherAlgorithm, int keySize) {
        try {
            // 创建密码器
            Cipher cipher = Cipher.getInstance(cipherAlgorithm);

            byte[] byteContent = content.getBytes("utf-8");

            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(key, keyAlgorithm, keySize));

            // 加密
            byte[] result = cipher.doFinal(byteContent);

            // 通过Base64转码返回
            return Base64.getEncoder().encodeToString(result);
        } catch (Exception ex) {
            logger.error("", ex);
        }
        return null;
    }

    /**
     * DES 解密操作
     *
     * @param content
     * @param key
     * @param keyAlgorithm
     * @param cipherAlgorithm
     * @param keySize         秘钥长度
     * @return
     */
    public static String decrypt(String content, String key, String keyAlgorithm, String cipherAlgorithm, int keySize) {

        try {
            // 实例化
            Cipher cipher = Cipher.getInstance(cipherAlgorithm);

            // 使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(key, keyAlgorithm, keySize));

            // 执行操作
            byte[] result = cipher.doFinal(Base64.getDecoder().decode(content));

            return new String(result, "utf-8");
        } catch (Exception ex) {
            logger.error("", ex);
        }

        return null;
    }

    /**
     * 生成加密秘钥
     *
     * @param keyAlgorithm
     * @return
     */
    private static SecretKeySpec getSecretKey(final String key, String keyAlgorithm, int keySize) {
        // 返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg;

        try {
            kg = KeyGenerator.getInstance(keyAlgorithm);

            // DES 要求密钥长度为 56
            kg.init(keySize, new SecureRandom(key.getBytes()));

            // 生成一个密钥
            SecretKey secretKey = kg.generateKey();

            // 转换为DES专用密钥
            return new SecretKeySpec(secretKey.getEncoded(), keyAlgorithm);
        } catch (NoSuchAlgorithmException ex) {
            logger.error("", ex);
        }

        return null;
    }
}
