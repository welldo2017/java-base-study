package com.welldo.zero.data_type_1;

/**
 * 对于整型类型，Java只定义了带符号的整型，因此，最高位的bit表示符号位（0表示正数，1表示负数）。
 * 各种整型能表示的最大范围如下：
 * byte：-128 ~ 127
 * short: -32768 ~ 32767
 * int: -2147483648 ~ 2147483647
 * long: -9223372036854775808 ~ 9223372036854775807
 *
 *
 * @author welldo
 * @date 2020/8/6
 */
public class DataTypeInt {
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
    }

}
