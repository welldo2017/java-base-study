package com.welldo.zero.data_type_1;

/**
 * 浮点型 * flout double
 *
 * 浮点类型的数就是小数，因为小数用科学计数法表示的时候，小数点是可以“浮动”的，
 * 如1234.5可以表示成1234.5x10^0, 也可以12.345x10^2，也可以1.2345x10^3，所以称为浮点数。
 *
 *
 * 浮点数可表示的范围非常大，
 * float类型可最大表示3.4028235x10^38 (3.4028235E38)， * 而double类型可最大表示1.79x10^308
 *
 * 对于 float 类型，后缀为:f / F ,必须加
 * 对于 double 类型，后缀为 d / D, 可加可不加
 *
 *
 *
 * @author welldo
 * @date 2020/8/6
 */
public class DataTypeFlout {
    public static void main(String[] args) {

        float f1 = 3.14F;
        float f2 = 3.14e38f; // 科学计数法表示的3.14x10^38

        double d = 1.79e308;
        double d2 = -1.79e308;
        double d3 = 4.9e-324; // 科学计数法表示的4.9x10^-324

    }
}
