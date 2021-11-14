package com.welldo.zero.a2_calc;

/**
 * 字符和字符串
 *
 * 1. 字符
 * Java在内存中总是使用Unicode来编码字符
 * 一个英文字符和一个中文字符都用一个char表示, 用Unicode编码时, 占用两个字节.
 *
 *
 * 2.字符串类型
 *
 * 常见的转义字符包括：
 * \" 表示字符"
 * \' 表示字符'
 * \\ 表示字符\
 * \n 表示换行符
 * \r 表示回车符
 * \t 表示Tab
 * \U#### 表示一个Unicode编码的字符
 *
 *
 *
 *
 *
 * 不可变特性***********************************
 *
 * 执行String s = "hello";时，JVM虚拟机先创建字符串"hello"，然后，把字符串变量s指向它：
 *
 *       s
 *       │
 *       ▼
 * ┌───┬───────────┬───┐
 * │   │  "hello"  │   │
 * └───┴───────────┴───┘
 * 紧接着，执行s = "world";时，JVM虚拟机先创建字符串"world"，然后，把字符串变量s指向它：
 *
 *       s ──────────────┐
 *                       │
 *                       ▼
 * ┌───┬───────────┬───┬───────────┬───┐
 * │   │  "hello"  │   │  "world"  │   │
 * └───┴───────────┴───┴───────────┴───┘
 * 原来的字符串"hello"还在，只是我们无法通过变量s访问它而已。
 * 因此，字符串的不可变是指字符串内容不可变。
 *
 *
 *
 * @author welldo
 * @date 2020/8/7
 */
public class CharCalc7 {
    public static void main(String[] args) {

        //要显示一个字符的Unicode编码，只需将char类型直接赋值给int类型即可：
        int n1 = 'A'; // 字母“A”的Unicodde编码是65
        int n2 = '中'; // 汉字“中”的Unicode编码是20013
        System.out.println(n1);
        System.out.println(n2);


        String s0 = ""; // 空字符串，包含0个字符
        String s1 = "A"; // 包含一个字符
        String s2 = "ABC"; // 包含3个字符
        String s3 = "中文 ABC"; // 包含6个字符，其中有一个空格


        /*
        Java的字符串除了是一个引用类型外，还有个重要特点，就是字符串不可变。考察以下代码：
        请查看文件头注释
         */
        String s = "hello";
        System.out.println(s); // 显示 hello
        s = "world";
        System.out.println(s); // 显示 world





    }
}
