package com.welldo.zero.a4_oop.core_class;

import com.welldo.zero.a1_data_type.DataTypeInt_2;

/**
 * 包装类型
 *
 * 0.我们已经知道，Java的数据类型分两种：
 *  基本类型：byte，short，int，long，boolean，float，double，char
 *  引用类型：所有class和interface,数组,枚举
 *
 * 0.因为包装类型非常有用，Java核心库为每种基本类型都提供了对应的包装类型：
 *  |--------------------------------
 *  |基本类型	    对应的引用类型
 *  |--------------------------------
 *  |boolean	java.lang.Boolean
 *  |byte	    java.lang.Byte
 *  |short	    java.lang.Short
 *  |int	    java.lang.Integer
 *  |long	    java.lang.Long
 *  |float	    java.lang.Float
 *  |double	    java.lang.Double
 *  |char	    java.lang.Character
 *  |--------------------------------
 *
 * 1. 没有自动装箱的写法,看代码
 *
 *
 * 2.Auto Boxing
 * Java编译器可以帮助我们自动在int和Integer之间转型：
 * 这种直接把int变为Integer的赋值写法，称为自动装箱（Auto Boxing），反过来，把Integer变为int的赋值写法，称为自动拆箱（Auto Unboxing）。
 * (注意：自动装箱和自动拆箱只发生在编译阶段,JDK>=1.5，目的是为了少写代码。)
 * 装箱和拆箱会影响代码的执行效率，并且，自动拆箱执行时可能会报NullPointerException：
 *
 * 3.不变类
 * 所有的包装类型都是不变类。我们查看Integer的源码可知，它的核心代码如下：
 *  public final class Integer {
 *      private final int value;    //因此，一旦创建了Integer对象，该对象就是不变的。
 *  }
 *  对两个Integer实例进行比较要特别注意：绝对不能用==比较，因为Integer是引用类型，必须使用equals()比较
 *  查看{@link DataTypeInt_2}的思考题
 *
 * 4.进制转换
 * Integer类本身还提供了大量方法
 *
 * 5.所有的整数和浮点数的包装类型都继承自Number，因此，可以非常方便地直接通过包装类型获取各种基本类型：
 *
 * 6.无符号整型和有符号整型的转换在Java中就需要借助包装类型的静态方法完成。
 *
 * @author welldo
 * @date 2020/8/10
 */
public class PackageType4 {
    public static void main(String[] args) {
        // test1();
        // test2();
        // test4();
        test5();
    }


    //6.因为byte的-1的二进制表示是11111111，以无符号整型转换后的int就是255
    static void  test6(){
        byte x = -1;
        byte y = 127;
        System.out.println(Byte.toUnsignedInt(x)); // 255
        System.out.println(Byte.toUnsignedInt(y)); // 127

    }

    static void  test5(){
        // 向上转型为Number:
        Number num = new Integer(999);
        // 获取byte, int, long, float, double:
        byte b = num.byteValue();
        int n = num.intValue();
        long ln = num.longValue();
        float f = num.floatValue();
        double d = num.doubleValue();
        System.out.println(b);
    }


    static void  test4(){
        int x1 = Integer.parseInt("100"); // 100
        int x2 = Integer.parseInt("100", 16); // 256,因为按16进制解析
        System.out.println(x1);
        System.out.println(x2);
    }

    //2.
    static void  test2(){
        Integer n = 1000; //装箱 编译器自动使用Integer.valueOf(int)
        int x = n;      //拆箱 编译器自动使用Integer.intValue()
        n =1001;

        // Integer n1 = null;//空指针异常
        // int i = n1;
    }

    //1.手动装箱
    static void  test1(){
        int i = 100;
        Integer n1 = new Integer(i);// 通过new操作符创建Integer实例(不推荐使用,会有编译警告):

        Integer n2 = Integer.valueOf(i);// 通过静态方法valueOf(int)创建Integer实例:
        Integer n3 = Integer.valueOf("100");// 通过静态方法valueOf(String)创建Integer实例:
        System.out.println(n3.intValue());
    }
}
