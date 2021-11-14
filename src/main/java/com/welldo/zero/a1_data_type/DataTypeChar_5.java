package com.welldo.zero.a1_data_type;

import com.welldo.zero.a4_oop.core_class.StrAndEncode2;

/**
 * 字符类型
 *       ┌───┬───┐
 *  char │   │   │
 *       └───┴───┘
 *
 * 字符类型char表示一个字符
 * 注意char类型使用单引号'，且仅有一个字符，要和双引号"的字符串类型区分开。
 *
 * Java的char类型除了可表示标准的ASC II(一个字节)外，还可以表示一个Unicode(两个字节,甚至更多)字符：详情{@link StrAndEncode2}
 * 表面上看,char占用2个8位, 实际上占用1个16位, 但长度都是两个字节,解释如下:
 *
 * The char data type is a single 16-bit Unicode character.
 * It has a minimum value of '\u0000' (or 0) and a maximum value of '\uffff' (or 65,535 inclusive).
 *
 * @author welldo
 * @date 2020/8/6
 */
public class DataTypeChar_5 {
    public static void main(String[] args) {
        char a = 'A';
        char zh = '中';
        System.out.println(a);
        System.out.println(zh);
    }


}
