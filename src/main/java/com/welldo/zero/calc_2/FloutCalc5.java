package com.welldo.zero.calc_2;

import com.welldo.zero.oop_4.core_class.BigDecimal8;

/**
 * 浮点数运算和整数运算相比，只能进行 加减乘除 这些数值计算，不能做位运算和移位运算。
 *
 * !!!
 * 在计算机中，浮点数虽然表示的范围大，但是，浮点数有个非常重要的特点，就是浮点数常常无法精确表示。
 * 浮点数的运算结果可能有误差
 *
 *
 * 举个栗子：
 * 浮点数0.1在计算机中就无法精确表示，因为十进制的0.1换算成二进制是一个无限循环小数，
 * 很显然，无论使用float还是double，都只能存储一个0.1的近似值。
 * 但是，0.5这个浮点数又可以精确地表示。
 * 因为浮点数常常无法精确表示，因此，浮点数运算会产生误差：
 *
 * !!! 我们不需要研究哪些情况会精确, 哪些情况不精确.
 * !!! 解决方案, 不使用Flout double 进行计算, 而使用 bigDecimal {@link BigDecimal8}
 *
 *
 * !!!
 * 整型和浮点型运算时，整型会自动提升为浮点型；
 * 因为CPU运算的时候，比如做加法，只能整数+整数，或者浮点数+浮点数，没有整数+浮点数的功能。
 * 除法也是一样：
 * 24/5: 整数/整数，OK，结果也是整数4
 * 24.0/5.0: 浮点数/浮点数，OK，结果也是浮点数4.8
 * 24/5.0: 整数/浮点数，直接算不行，必须把整数提升为浮点数再算
 * 自动提升类型是编译器的功能
 *
 * @author welldo
 * @date 2020/8/6
 */
public class FloutCalc5 {
    public static void main(String[] args) {
        System.out.println("!!!! 无法精确表示!!!");
        System.out.println(1 - 0.9);//0.1 无法精确表示
        System.out.println(0.05+0.01);
        System.out.println(1.0-0.42);
        System.out.println(4.015*100);
        System.out.println(123.3/100);

        //溢出
        //整数运算, 在除数为0时会报错; 而浮点数运算,在除数为0时，不会报错，但会返回几个特殊值：
        //在实际运算中很少碰到，了解即可。
        System.out.println(0.0 / 0); // NaN
        System.out.println(1.0 / 0);// Infinity
        System.out.println(-1.0 / 0);// -Infinity


        //强制转型
        // 可以将浮点数强制转型为整数。在转型时，浮点数的小数部分会被丢掉。
        // 如果转型后超过了整型能表示的最大范围，将返回整型的最大值。例如：
        System.out.println("------------------");
        System.out.println((int) 12.3);//12
        System.out.println((int) 12.7);//12
        System.out.println((int) -12.7);//-12
        System.out.println((int) (12.7 + 0.5)); //13
        System.out.println((int) 1.2e20); //2147483647
        System.out.println(Integer.MAX_VALUE); //2147483647



        // 如果要进行四舍五入，可以对正浮点数加上0.5再强制转型; 对负浮点数-0.5再强制转型.
        // 四舍五入API: Math.round()
        System.out.println("------------------");
        double d = -2.5;
        int n = (int) (d - 0.5);
        System.out.println(n);
    }
}
