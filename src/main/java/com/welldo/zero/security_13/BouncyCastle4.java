package com.welldo.zero.security_13;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * 0.
 * BouncyCastle是一个提供了很多哈希算法和加密算法的第三方库。
 * 它提供了Java标准库没有的一些算法，例如，RipeMD160哈希算法。
 *
 *
 * 1. java标准库的java.security包提供了一种标准机制，允许第三方提供商无缝接入。
 * 我们要使用BouncyCastle提供的RipeMD160算法，需要先把BouncyCastle注册一下：
 *
 * @author welldo
 * @date 2020/9/1
 */
public class BouncyCastle4 {
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        /**
         * 1.注册BouncyCastle:
         */
        Security.addProvider(new BouncyCastleProvider());//注册

        // 按名称正常调用:
        MessageDigest md = MessageDigest.getInstance("RipeMD160");
        md.update("HelloWorld".getBytes("UTF-8"));
        byte[] result = md.digest();
        System.out.println(new BigInteger(1, result).toString(16));
    }
}
