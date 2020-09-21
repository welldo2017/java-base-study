package com.welldo.zero.data_type_1;

import com.welldo.zero.oop_4.core_class.PackageType4;

/**
 * 对于整型类型，Java只定义了带符号的整型，并没有无符号整型（Unsigned）,因此，最高位的bit表示符号位（0表示正数，1表示负数）。
 * 各种整型能表示的最大范围如下：
 * byte：-128 ~ 127
 * short: -32768 ~ 32767
 * int: -2147483648 ~ 2147483647
 * long: -9223372036854775808 ~ 9223372036854775807
 *
 * 0.
 * 从上一节我们知道, 一个字节就是一个8位二进制数，即8个bit。
 * 它的二进制表示范围从00000000~11111111，换算成十进制是0~255，
 * byte是有符号整型，范围是-128~+127，但如果把byte看作无符号整型，它的范围就是0~255。
 *
 * 1.
 * 而C语言则提供了CPU支持的全部数据类型，包括无符号整型。
 * 无符号整型和有符号整型的转换在Java中就需要借助包装类型的静态方法完成。请参考{@link PackageType4} 第6点
 *
 * 2. 思考题: 为什么 Java 的integer “1000==1000”为false，而”100==100“为true？
 *
 * @author welldo
 * @date 2020/8/6
 */
public class DataTypeInt_1 {
    public static void main(String[] args) {

        //int最大值
        int i0 = Integer.MAX_VALUE;
        int i1 = 2147483647;
        int i3 = 2_147_483_647; // 加下划线更容易识别

        //int最小值
        int i2 = Integer.MIN_VALUE;
        int i4 = -2147483648;


        System.out.println(i0);
        System.out.println(i1 == i0);
        System.out.println(i3 == i0);

        System.out.println(i2);
        System.out.println(i2 == i4);

        test1();
    }

    /**
     * 为什么 Java 的integer “1000==1000”为false，而”100==100“为true？
     * 因为自动装箱过程中, 调用了Integer 的valueOf(int i) 方法,参考{@link PackageType4}
     *      public static Integer valueOf(int i) {...}
     * 1. 如果值的范围在-128到127之间，此方法走integer的内部类IntegerCache.java的分支，
     *      此内部类缓存了从-128到127之间的所有的整数对象。它就从高速返回已经缓存的实例。
     * 2. 而其余的值,则走new 分支
     *
     * 为什么这里需要缓存？
     * 合乎逻辑的理由是，“小”整数使用率比大整数要高，因此，缓存起来，可以减少潜在的内存占用。
     */
    static void test1(){
        Integer a = 1000, b = 1000;
        Integer c = 100, d = 100;

        System.out.println(a == b);//idea 会友情提示:Condition 'c == d' is always 'false'
        System.out.println(c == d);//idea 会友情提示:Condition 'c == d' is always 'true'
    }

}
