package com.welldo.zero.oop_4.core_class;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * BigDecimal
 *
 * 和BigInteger类似，BigDecimal可以表示一个任意大小且精度完全准确的浮点数。
 * BigDecimal用于表示精确的小数，常用于财务计算
 *
 * 0.
 * 使用BigDecimal，但一定要用BigDecimal(String)构造器，而千万不要用BigDecimal(double)来构造
 *
 *
 * 1.
 * BigDecimal用scale()表示小数位数
 *
 *
 * 2.
 * stripTrailingZeros()方法，可以将一个BigDecimal格式化为一个相等的，但去掉了末尾0的BigDecimal：
 * 2.5
 * 如果在stripTrailingZeros() 后, 再scale()返回负数，例如，-2，表示这个数之前是个整数，并且末尾有2个0。
 *
 *
 * 3.
 * 可以对一个BigDecimal设置它的scale，如果精度比原始值低，那么按照指定的方法进行四舍五入或者直接截断：
 *
 * 4.
 * 对BigDecimal做加、减、乘时，精度不会丢失，
 * 但是做除法时，存在无法除尽的情况，这时，就必须指定精度以及如何进行截断：
 *
 *
 * 5.
 * 还可以对BigDecimal做除法的同时求余数：
 * 返回的数组包含两个BigDecimal，分别是商和余数，其中商总是整数，余数不会大于除数
 * 我们可以利用这个方法判断两个BigDecimal是否是整数倍数：
 *
 * 6.比较BigDecimal
 * equals()方法, 不但要求两个BigDecimal的值相等，还要求它们的scale()相等：
 * compareTo()方法, 它根据两个值的大小分别返回负数、正数和0，分别表示小于、大于和等于
 *
 * 所以:  最好使用compareTo()比较两个BigDecimal的值，不要使用equals()！
 *
 * @author welldo
 * @date 2020/8/11
 */
public class BigDecimal8 {

    public static void main(String[] args) {
        //1.使用BigDecimal，但一定要用BigDecimal(String)构造器，而千万不要用BigDecimal(double)来构造
        BigDecimal d1 = new BigDecimal("123.45");
        BigDecimal d2 = new BigDecimal("123.4500");
        BigDecimal d3 = new BigDecimal("1234500");
        System.out.println(d1.scale()); // 2,两位小数
        System.out.println(d2.scale()); // 4
        System.out.println(d3.scale()); // 0

        //2.
        BigDecimal d22 = d2.stripTrailingZeros();
        System.out.println(d22.scale());//4--> 2

        //2.5
        BigDecimal d33 = d3.stripTrailingZeros();
        System.out.println(d33);//打印后是科学记数法
        System.out.println(d33.scale());// 0--> -2

        //3.
        BigDecimal d4 = new BigDecimal("123.456789");
        BigDecimal d41 = d4.setScale(4, RoundingMode.HALF_UP);//四舍五入
        BigDecimal d42 = d4.setScale(4, RoundingMode.DOWN);//直接截断
        System.out.println(d41);
        System.out.println(d42);

        //4.
        BigDecimal d5 = new BigDecimal("123.456");
        BigDecimal d6 = new BigDecimal("23.456789");
        BigDecimal d51 = d5.divide(d6, 10, RoundingMode.HALF_UP); // 保留10位小数并四舍五入
        // BigDecimal d52 = d5.divide(d6); // 报错：ArithmeticException，因为除不尽
        System.out.println(d51);


        //5.返回的数组包含两个BigDecimal，分别是商和余数，其中商总是整数，余数不会大于除数
        BigDecimal n = new BigDecimal("12.345");
        BigDecimal m = new BigDecimal("0.12");
        BigDecimal[] dr = n.divideAndRemainder(m);
        System.out.println(dr[0]); // 102
        System.out.println(dr[1]); // 0.105
        if (dr[1].signum() == 0) {
            System.out.println("n是m的整数倍");
        }


        //6.
        BigDecimal d7 = new BigDecimal("123.456");
        BigDecimal d8 = new BigDecimal("123.45600");
        System.out.println(d7.equals(d8)); // false,因为scale不同
        System.out.println(d7.equals(d8.stripTrailingZeros())); // true,因为d2去除尾部0后scale变为2
        System.out.println(d7.compareTo(d8)); // 0
    }
}
