package com.welldo.zero.calc_2;

/**
 * 布尔运算
 *
 * @author welldo
 * @date 2020/8/7
 */
public class BooleanCalc6 {
    public static void main(String[] args) {

        //短路运算
        //布尔运算的一个重要特点是短路运算。如果一个布尔运算的表达式能提前确定结果，则后续的计算不再执行，直接返回结果。

        //因为false && x的结果总是false，因此，与运算在确定第一个值为false后，不再继续计算，而是直接返回false。
        // 除数为0,但是没报错,说明 5/0 确实没有被运算.
        boolean result = false && (5 / 0 > 0);
        System.out.println(result);
    }
}
