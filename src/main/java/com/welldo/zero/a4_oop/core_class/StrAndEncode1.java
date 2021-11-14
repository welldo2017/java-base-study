package com.welldo.zero.a4_oop.core_class;

/**
 * 字符串和编码
 *
 *
 * 1. 字符串在String内部是通过一个char[]数组表示的， * private final char value[];
 * 因此，按下面的写法也是可以的：
 * String s2 = new String(new char[] {'H', 'e', 'l', 'l', 'o', '!'});
 *
 *
 * 2.字符串比较( == 与 equals方法)
 *
 *
 * 3.常用方法(不举例)
 * trim(),
 * 另一个strip()方法也可以移除字符串首尾空白字符。它和trim()不同的是，类似中文的空格字符\u3000也会被移除：
 * strip(),stripLeading(),stripTrailing()
 *
 *
 * 4. 格式化字符串
 * 字符串提供了formatted()方法和format()静态方法，可以传入其他参数，替换占位符，然后生成新的字符串：
 * formatted() 方法,since java13
 *
 * 有几个占位符，后面就传入几个参数。参数类型要和占位符一致。我们经常用这个方法来格式化信息。常用的占位符有：
 * %s：显示字符串；
 * %d：显示整数；
 * %x：显示十六进制整数；
 * %f：显示浮点数。
 * %.2f：显示浮点数, 小数点后两位, 有四舍五入功能
 *
 * 如果你不确定用啥占位符，那就始终用%s，因为%s可以显示任何数据类型。
 *
 *
 * 5. 类型转换
 * 5.1 要把任意基本类型或引用类型转换为字符串，可以使用静态方法valueOf()。这是一个重载方法，编译器会根据参数自动选择合适的方法
 * 5.2 要把字符串转换为其他类型，就需要根据情况
 * 5.3 转换成 char[]
 *
 *
 *
 * @author welldo
 * @date 2020/8/9
 */
public class StrAndEncode1 {

    public static void main(String[] args) {

        /**
         * 2.
         * 2.1 使用==的时候:
         * 比较引用值, 也就是地址值, true.
         * 原因,编译器在编译期，会自动把所有相同的字符串当作一个对象放入常量池，自然s1和s2的引用就是相同的。
         * 所以，这种==比较返回true纯属巧合。换一种写法，==比较就会失败
         *
         * 2.2 使用equals方法:比较字符串的"内容" ,true
         */
        String s1 = "hello";
        String s2 = "hello";

        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));

        //换一种写法，==比较就会失败
        String s3 = "hello";
        String s4 = "HELLO".toLowerCase();
        System.out.println(s3 == s4);       //false
        System.out.println(s3.equals(s4));  //true


        // 4.
        System.out.println(String.format("Hi %s, your score is %.2f!", "Bob", 59.566));


        //5.1 把任意基本类型或引用类型转换为字符串
        System.out.println(String.valueOf(45.67));
        System.out.println(String.valueOf(true));
        System.out.println(String.valueOf(new Object()));// 类似java.lang.Object@636be97c

        //5.2 把字符串转换为其他类型，就需要根据情况
        int n1 = Integer.parseInt("123"); // 123
        int n2 = Integer.parseInt("ff", 16); // 按十六进制转换，255
        boolean b1 = Boolean.parseBoolean("true"); // true
        boolean b2 = Boolean.parseBoolean("FALSE"); // false
        System.out.println("===========================");


        //5.3
        new StrAndEncode1().test();
    }


    /**
     * 5.3
     * String和char[]类型可以互相转换
     * 思考: 如果修改了char[]数组，String会改变吗?
     * 答案 : 不会;
     *
     * 因为通过new String(char[])创建新的String实例时，它并不会直接引用传入的数组，
     * 而是会调用 Arrays.copyOf(value, value.length)方法, 把char[]复制一份，
     * 所以，修改外部的char[]数组不会影响String实例内部的char[]数组，
     * 因为这是两个不同的数组。
     *
     * 从String的不变性设计可以看出，如果传入的对象有可能改变，我们需要复制而不是直接引用。
     */
    public void test (){
        char[] cs = "Hello".toCharArray();// String -> char[]
        String s = new String(cs); // char[] -> String
        System.out.println(s);

        cs[0] = 'X';
        System.out.println(s);
    }
}
