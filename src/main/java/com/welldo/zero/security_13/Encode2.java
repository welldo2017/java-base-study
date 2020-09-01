package com.welldo.zero.security_13;

import com.welldo.zero.oop_4.core_class.StrAndEncode2;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

/**
 * 编码算法
 *
 * 0. 基础阅读:{@link StrAndEncode2}
 *
 *
 * 1.URL编码
 * URL编码是浏览器发送数据给服务器时使用的编码，它通常附加在URL的参数部分，例如：
 * https://www.baidu.com/s?wd=%E4%B8%AD%E6%96%87
 *
 * 之所以需要URL编码，是因为出于兼容性考虑，很多服务器只识别ASCII字符。但如果URL中包含中文、日文这些非ASCII字符怎么办？
 * 不要紧，URL编码有一套规则：
 *      如果字符是A~Z，a~z，0~9以及 "中横线,下划线,点号,星号" 则保持不变；
 *      如果是其他字符，先转换为UTF-8编码，然后对每个字节以%XX表示。
 *      例如："中"的UTF-8编码是0xe4b8ad，因此，它的URL编码是%E4%B8%AD。URL编码总是大写。
 *
 * Java标准库提供了一个URLEncoder / URLDecoder 类来对任意字符串进行URL编码/解码
 * !!!
 * URL编码是编码算法，不是加密算法。URL编码的目的是把任意文本数据编码为%前缀表示的文本，
 * 编码后的文本仅包含A~Z，a~z，0~9，"中横线,下划线,点号,星号" 和%，便于浏览器和服务器处理。
 *
 *
 * 2.Base64编码
 * URL编码是对字符进行编码，表示成%xx的形式，而Base64编码是对任意二进制数据进行编码，表示成文本格式。
 *
 * Base64编码可以把任意长度的二进制数据变为纯文本，且只包含A~Z、a~z、0~9、+、/、=这些字符。
 * 它的原理是把3字节(共24bit)的二进制数据按6bit一组，用4个int整数表示，然后查表，把int整数用索引对应到字符，得到编码后的字符串。
 *
 * 举个例子：3个byte数据分别是e4、b8、ad，按6bit分组得到39、0b、22和2d：
 * ┌───────────────┬───────────────┬───────────────┐
 * │      e4       │      b8       │      ad       │
 * └───────────────┴───────────────┴───────────────┘
 * ┌─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┐
 * │1│1│1│0│0│1│0│0│1│0│1│1│1│0│0│0│1│0│1│0│1│1│0│1│
 * └─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┘
 * ┌───────────┬───────────┬───────────┬───────────┐
 * │    39     │    0b     │    22     │    2d     │
 * └───────────┴───────────┴───────────┴───────────┘
 * 因为6位整数的范围总是0~63，所以，能用64个字符表示：
 * 字符A~Z对应索引0~25，
 * 字符a~z对应索引26~51，
 * 字符0~9对应索引52~61，
 * 最后两个索引62、63分别用字符+和/表示。
 *
 * 如果输入的byte[]数组长度不是3的整数倍肿么办？这种情况下，需要对输入的末尾补一个或两个0x00，
 * 编码后，在结尾加一个=表示补充了1个0x00，加两个=表示补充了2个0x00，解码的时候，去掉末尾补充的一个或两个0x00即可。
 *
 * Base64编码的缺点是传输效率会降低，因为它把原始数据的长度增加了1/3。
 *
 *
 *
 *
 *
 * @author welldo
 * @date 2020/9/1
 */
public class Encode2 {
    public static void main(String[] args) throws UnsupportedEncodingException {

        /*
         * 1.Java标准库提供了一个URLEncoder类来对任意字符串进行URL编码：
         * URLEncoder把空格字符编码成+
         * 而现在的URL编码标准要求空格被编码为%20
         */
        String encode = URLEncoder.encode("中文 ", String.valueOf(StandardCharsets.UTF_8));
        System.out.println(encode);//%E4%B8%AD%E6%96%87

        String decoded = URLDecoder.decode("%E4%B8%AD%E6%96%87%21", String.valueOf(StandardCharsets.UTF_8));
        System.out.println(decoded);



        /*
         * 2.在Java中，二进制数据就是byte[]数组。Java标准库提供了Base64来对byte[]数组进行编解码：
         */
        byte[] input = new byte[] { (byte) 0xe4, (byte) 0xb8, (byte) 0xad };
        String b64encoded = Base64.getEncoder().encodeToString(input);
        System.out.println(b64encoded);// "中" 编码后为 5Lit

        byte[] output = Base64.getDecoder().decode("5Lit");//解码
        System.out.println(Arrays.toString(output)); // [-28, -72, -83]

    }
}
