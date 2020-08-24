package com.welldo.zero.oop_4.core_class;

import com.welldo.zero.data_type_1.DataTypeChar;

/**
 * 字符串和编码
 *
 * 1.
 * ╔════════════╦══════╦════════════════=═╦════════ ═╗
 * ║ 编码        ║ 字节数║ 适用于            ║ 制定者    ║
 * ╠════════════╬══════╬════════════════=═╬═════════ ╣
 * ║ ASC II     ║ 1    ║ 英文.数字.常用符号  ║ 美国标准学会║
 * ║ GB2312     ║ 2    ║ 一个汉字           ║ 中国      ║
 * ║ shift jis  ║ x    ║ 日文              ║ 日本      ║
 * ╚════════════╩══════╩═════════════════=╩═════════=╝
 * 缺点: 各自为阵, 不统一, 强行统一使用的话,有冲突;
 *
 * 2.
 * 全球统一码联盟发布了 Unicode 编码，它把世界上主要语言都纳入同一个编码, Unicode编码需要两个字节(甚至更多)
 *          ┌────┐
 * ASCII:   │ 41 │
 *          └────┘
 *          ┌────┬────┐
 * Unicode: │ 00 │ 41 │
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
 *
 * 4. 在Java中，char类型,一个字符占用两个字节, 并且用Unicode编码  {@link DataTypeChar}
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
 *
 *
 * @author welldo
 * @date 2020/8/9
 */
public class StrAndEncode2 {


}
