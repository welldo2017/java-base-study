package com.welldo.zero.a4_oop.core_class;

import java.math.BigInteger;

/**
 * BigInteger
 *
 * 在Java中，由CPU原生提供的整型, 最大范围是64位long型整数。使用long型整数可以直接通过CPU指令进行计算，速度非常快。
 *
 * 如果我们使用的整数范围超过了long型怎么办？这个时候，就只能用软件来模拟一个大整数。
 * java.math.BigInteger就是用来表示任意大小的整数
 * 和long型整数运算比，BigInteger不会有范围限制，但缺点是速度比较慢。
 *
 *
 * 1.
 * 对BigInteger做运算的时候，只能使用实例方法，例如，加法运算
 *
 * 2.
 * BigInteger和Integer、Long一样，也是不可变类，并且也继承自Number类。因为Number定义了转换为基本类型的几个方法：
 * 转换为byte：byteValue()
 * 转换为short：shortValue()
 * 转换为int：intValue()
 * 转换为long：longValue()
 * 转换为float：floatValue()
 * 转换为double：doubleValue()
 * !!! 如果BigInteger表示的范围超过了基本类型的范围，转换时将丢失高位信息，即结果不一定是准确的
 *
 *
 * 3.
 * 如果需要准确地转换成基本类型，可以使用intValueExact()、longValueExact()等方法，
 * 在转换时如果超出范围，将直接抛出ArithmeticException异常。
 *
 *
 *
 * @author welldo
 * @date 2020/8/11
 */
public class BigInteger7 {
    public static void main(String[] args) {

        long maxValue = Long.MAX_VALUE;
        System.out.println(maxValue);// long 最大值: 9223372036854775807

        BigInteger i1 = new BigInteger("111");
        BigInteger i2 = new BigInteger("222");
        //1.
        System.out.println(i1.add(i2));


        //2.
        BigInteger i = new BigInteger("9223372036854775807");
        System.out.println(i.longValue()); // 正好在long范围之内
        System.out.println(i.add(i).longValue());//丢失精度

        //3.
        // System.out.println(i.add(i).longValueExact());


    }
}
