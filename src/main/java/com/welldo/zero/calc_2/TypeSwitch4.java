package com.welldo.zero.calc_2;

/**
 * 类型自动提升与强制转型
 * 在运算过程中，如果参与运算的两个数类型不一致，那么计算结果为较大类型的整型。
 *
 * 特别注意: byte, short,int 3种其中任意2种进行计算，都会自动转型为int,再计算.
 *
 * @author welldo
 * @date 2020/8/6
 */
public class TypeSwitch4 {
    public static void main(String[] args) {
        short s = 1234;
        int i = 123456;
        int x = s + i; // s自动转型为int
        // short y = s + i; // 将大类型, 赋值给小类型, 则编译错误!


        /*
        强制转型，即将大范围的整数转型为小范围的整数。
        例如，将int强制转型为short：
        要注意，超出范围的强制转型会得到错误的结果，原因是转型时，int的两个高位字节直接被扔掉，仅保留了低位的两个字节：
         */
        short s2 = (short) 12345; // 12345
        System.out.println(s2);

        short s1 = (short) 1234567; // -10617 todo 没懂
        System.out.println(s1);

    }
}
