package com.welldo.zero.calc_2;

import java.util.Arrays;

/**
 *
 * 二维数组
 * 二维数组就是数组的数组
 *
 * 二维数组的每个数组元素的长度并不要求相同
 *
 *                     ┌───┬───┬───┬───┐
 *          ┌───┐  ┌──>│ 1 │ 2 │ 3 │ 4 │
 * ns ─────>│░░░│──┘   └───┴───┴───┴───┘
 *          ├───┤      ┌───┬───┐
 *          │░░░│─────>│ 5 │ 6 │
 *          ├───┤      └───┴───┘
 *          │░░░│──┐   ┌───┬───┬───┐
 *          └───┘  └──>│ 7 │ 8 │ 9 │
 *                     └───┴───┴───┘
 *
 * @author welldo
 * @date 2020/8/8
 */
public class Array9 {
    public static void main(String[] args) {
        // 要打印一个二维数组，可以使用两层嵌套的for循环：
        // 或者使用Java标准库的Arrays.deepToString()：

        int[][] ns = {
                { 1, 2, 3, 4 },
                { 5, 6, 7, 8 },
                { 9, 10, 11, 12 }
        };
        System.out.println(Arrays.deepToString(ns));



    }
}
