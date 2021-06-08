package com.welldo.zero.oop_4.core_class;

import com.welldo.zero.data_type_1.DataTypeChar_5;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * 字符串和编码
 *
 * 我们为什么需要字符集
 *  a)只有在 字符串 与 字节数组 之间相互转化时, 才需要用到字符集
 *  b)字节数组,可以存放在硬盘中,也可以在网络中传输;
 *  c)乱码的根本原因,是字符串转化为字节数组时, 和字节数组转化为字符串时, 使用的字符集不同。
 *  https://mp.weixin.qq.com/s?__biz=MjM5MjAwODM4MA==&mid=2650711726&idx=3&sn=cab046de52787ae1493625e46b8544ad&chksm=bea6d77d89d15e6b7bd3843ad0d7530ef8534ea05d84aeb7d25b3b82af6dd60850347fecce33&scene=21#wechat_redirect
 *
 * 0. 早期情况
 * 美国国家标准学会（American National Standard Institute：ANSI）制定了一套英文字母、数字和常用符号的编码，
 * 它占用一个字节，编码范围从0到127，最高位始终为0，称为ASC-II编码。
 * 例如，字符'A'的编码是0x41，字符'1'的编码是0x31。
 *
 * 缺点: 最多只能有127个字符
 *
 * 1.
 * ╔════════════╦══════╦════════════════=═╦════════ ═╗
 * ║ 编码        ║ 字节数║ 适用于            ║ 制定者    ║
 * ╠════════════╬══════╬════════════════=═╬═════════ ╣
 * ║ ASC II     ║ 1    ║ 英文.数字.常用符号  ║ 美国标准学会║
 * ║ GB2312     ║ 2    ║ 一个汉字           ║ 中国      ║
 * ║ shift jis  ║ x    ║ 日文              ║ 日本      ║
 * ╚════════════╩══════╩═════════════════=╩═════════=╝
 * 缺点: 这些编码因为标准不统一，同时使用，就会产生冲突。
 *
 *
 * 2.
 * 为了统一全球所有语言的编码，
 * 全球统一码联盟发布了 Unicode 编码，它把世界上主要语言都纳入同一个编码, Unicode编码需要两个字节(甚至更多)
 *
 * 英文字符'A'的ASCII编码和Unicode编码：(英文字符的Unicode编码就是简单地在前面添加一个00字节。)
 *          ┌────┐
 * ASCII:   │ 41 │
 *          └────┘
 *          ┌────┬────┐
 * Unicode: │ 00 │ 41 │
 *          └────┴────┘
 *
 * 中文字符'中'的GB2312编码和Unicode编码：
 *          ┌────┬────┐
 * GB2312:  │ d6 │ d0 │
 *          └────┴────┘
 *          ┌────┬────┐
 * Unicode: │ 4e │ 2d │
 *          └────┴────┘
 *
 * Unicode 缺点
 * 用 Unicode 编码英文字符,是简单地在ASCII前面添加一个00字节, 所以高字节总是00，包含大量英文的文本会浪费空间，
 *
 * 3. UTF-8
 * 所以，出现了UTF-8编码，它是一种变长编码，用来把固定长度的 Unicode 编码变成1～4字节的变长编码。
 * 通过UTF-8编码，英文字符'A'的编码为0x41，正好和ASCII码一致，
 * 而中文'中'的UTF-8编码为3字节0x e4 b8 ad。
 *
 * ============================
 * 汉字	| Unicode编码| UTF-8编码 | GB2312编码
 * =====|===========|==========
 * 中	| 0x4e2d	| 0xe4b8ad	| d6b0
 * 文	| 0x6587	| 0xe69687  | cec4
 * 编	| 0x7f16	| 0xe7bc96
 * 码	| 0x7801	| 0xe7a081
 * ============================
 * 它经常用来作为传输编码。
 *
 *
 * 4. 在Java中，char类型,一个字符占用两个字节, 并且用Unicode编码  {@link DataTypeChar_5}
 * !!!! 始终牢记：Java的String和char在内存中总是以Unicode编码表示。
 *
 *
 * 5. 延伸阅读
 * 对于不同版本的JDK，String有不同的优化。
 * 具体来说，早期JDK版本的String总是以char[]存储，
 * 而较新的JDK版本的String则以byte[]存储：
 *      如果String仅包含ASCII字符，则每个byte存储一个字符，否则，每两个byte存储一个字符，
 *      这样做的目的是为了节省内存，因为大量的长度较短的String通常仅包含ASCII字符：
 *
 *
 * 7.string.getBytes() 方法:
 *  使用平台的默认字符集,将这个string编码成一个字节序列，将结果存储到一个新的字节数组中。
 *
 * @author welldo
 * @date 2020/8/9
 */
public class StrAndEncode2 {
    public static void main(String[] args) throws UnsupportedEncodingException {

        //系统默认编码
        String csn = Charset.defaultCharset().name();
        System.out.println(csn);

        String hello = "中";

        // 按系统默认编码转换,不推荐这种写法
        System.out.println(" ----------------系统默认编码  ----------------");
        byte[] b1 = hello.getBytes();
        System.out.println(Arrays.toString(b1));//打印此字节数组


        //utf-8编码
        System.out.println(" ----------------utf-8编码----------------");
        byte[] b3 = hello.getBytes("utf-8");//或者 hello.getBytes(StandardCharsets.UTF_8);
        System.out.println(Arrays.toString(b3));//打印此字节数组
        System.out.println(byteArrayToHexString(b3));//打印16进制



        //UNICODE
        System.out.println(" ----------------UNICODE编码----------------");
        byte[] b2 = hello.getBytes("UNICODE");
        System.out.println(Arrays.toString(b2));
        System.out.println(byteArrayToHexString(b2));//打印16进制, todo 为何多了feff前缀?



        //GB2312
        System.out.println(" ----------------GB2312----------------");
        byte[] b4 = hello.getBytes("GB2312");
        System.out.println(Arrays.toString(b4));
        System.out.println(byteArrayToHexString(b4));//打印16进制
    }


    //字节数组转十六进制
    public static String byteArrayToHexString(byte[] data) {
        StringBuilder sBuilder = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            String str1 = Integer.toHexString(data[i] & 0xFF);
            sBuilder.append(str1);
        }
        return sBuilder.toString();
    }

    //十六进制转byte数组
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] bs = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            bs[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return bs;
    }


}
