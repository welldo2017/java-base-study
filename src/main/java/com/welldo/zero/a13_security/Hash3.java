package com.welldo.zero.a13_security;

import com.welldo.zero.a10_collection.EqualsAndHashCode6;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 哈希算法
 *
 * 0.
 * 哈希算法（Hash）又称摘要算法（Digest），它的作用是：对任意一组输入数据进行计算，得到一个固定长度的输出摘要。
 * 哈希算法最重要的特点就是：
 *      相同的输入一定得到相同的输出；
 *      不同的输入大概率得到不同的输出。
 *
 * 哈希算法的目的就是为了验证原始数据是否被篡改。
 * 哈希算法的另一个重要用途是存储用户密码
 *
 *
 * 1.Java字符串的hashCode()就是一个哈希算法, {@link EqualsAndHashCode6}
 *
 *
 * 2.哈希碰撞
 * 哈希碰撞是指，两个不同的输入得到了相同的输出：
 *      "AaAaAa".hashCode(); // 0x7460e8c0 或者1952508096
 *      "BBAaBB".hashCode(); // 0x7460e8c0 或者1952508096
 *
 * 碰撞能不能避免？答案是不能,碰撞是一定会出现的
 * 因为输出的字节长度是固定的，4字节整数，最多只有4294967296种输出，但输入的数据长度是不固定的，有无数种输入。
 * 所以，哈希算法是把一个无限的输入集合映射到一个有限的输出集合，必然会产生碰撞。
 *
 *
 *
 * 3.
 * 一个安全的哈希算法必须满足：
 *      碰撞概率低   (因为, 碰撞不可怕，我们担心的不是碰撞，而是碰撞的概率)
 *      不能猜测输出 (就是猜不出规律)
 *
 * 常用的哈希算法有：
 * =============|===============|============
 * 算法	        |输出长度（位）    |输出长度（字节）
 * =============|===============|============
 * MD5	        |128 bits	    |16 bytes       MD5因为输出长度较短，短时间内破解是可能的，目前已经不推荐使用。
 * SHA-1	    |160 bits	    |20 bytes       SHA-1是由美国国家安全局开发的
 * RipeMD-160	|160 bits	    |20 bytes
 * SHA-256	    |256 bits	    |32 bytes
 * SHA-512	    |512 bits	    |64 bytes
 * =============|===============|============
 * 根据碰撞概率，哈希算法的输出长度越长，就越难产生碰撞，也就越安全。
 * Java标准库提供了常用的哈希算法，并且有一套统一的接口。看代码
 *
 *
 * 4.MD5
 * 注意防止彩虹表攻击。 这个方法称之为加盐（salt）：
 * digest = md5(salt + password)
 *
 * 5.SHA-1
 * SHA算法实际上是一个系列，包括SHA-0（已废弃）、SHA-1、SHA-256、SHA-512等。
 * 看代码
 *
 * @author welldo
 * @date 2020/9/1
 */
public class Hash3 {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {

        /*
         * 2. 两者hashcode 都是0x7460e8c0或者1952508096
         */
        System.out.println("-----2-----");
        System.out.println("AaAaAa".hashCode());
        System.out.println("BBAaBB".hashCode());

        getsha1FromFile();

    }

     //3.Java标准库提供了常用的哈希算法，并且有一套统一的接口
    static void getMd5FromString() throws NoSuchAlgorithmException, UnsupportedEncodingException {

        System.out.println("-----3-----");
        // 创建一个MessageDigest实例:
        MessageDigest md = MessageDigest.getInstance("MD5");

        // 反复调用update输入数据:
        md.update("Hello".getBytes("UTF-8"));
        md.update("World".getBytes("UTF-8"));

        //调用digest()方法获得byte[]数组表示的摘要
        byte[] result = md.digest();
        //最后，把它转换为十六进制的字符串。
        System.out.println(new BigInteger(1, result).toString(16)); // 16 bytes: 68e109f0f40ca72a15e05cc22786f8e6
    }

    //获取文件的sha-1
    static void getsha1FromFile() throws NoSuchAlgorithmException, IOException {

        System.out.println("-----5 get sha1 From File-----");
        MessageDigest md = MessageDigest.getInstance("SHA-256");


        try (InputStream input = new FileInputStream("D:\\6.softWare\\chrome\\70.0.3538.110_chrome_installer.exe")) {//此文件有930个字节
            // 定义1024个字节大小的缓冲区:
            byte[] buffer = new byte[1024*1024];
            int n;
            while ((n = input.read(buffer)) != -1) { // 读取到缓冲区
                System.out.println("read " + n + " bytes.");

                // 反复调用update输入数据:
                md.update(buffer);
            }
        }

        byte[] result = md.digest();
        System.out.println(new BigInteger(1, result).toString(16));
    }

    //5. sha-1
    static void sha1() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        System.out.println("-----5-----");
        MessageDigest example5 = MessageDigest.getInstance("SHA-1");

        // 反复调用update输入数据:
        example5.update("Hello".getBytes("UTF-8"));
        example5.update("World".getBytes("UTF-8"));

        byte[] result5 = example5.digest();
        System.out.println(new BigInteger(1, result5).toString(16)); // 20 bytes: db8ac1c259eb89d4a131b253bacfca5f319d54f2
    }

}
