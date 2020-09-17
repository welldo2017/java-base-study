package com.welldo.zero.functional_20;

import com.welldo.zero.oop_4.base.Lambda15;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 0. Lambda基础
 * 请参考 {@link Lambda15}
 *
 *
 * 1. 在Java程序中，我们经常遇到一大堆单方法接口，即一个接口只定义了一个方法：
 *      Comparator
 *      Runnable
 *      Callable
 * 看代码
 * 接口的实现类 和 Lambda表达式 的比较
 *
 *
 * 2.方法引用
 * 是指如果某个方法签名和接口恰好一致，就可以直接传入方法引用。
 *      2.1 静态方法引用
 *      因为Comparator<String>接口定义的方法是int compare(String, String)，和静态方法int cmp(String, String)相比，
 *      方法参数一致，返回类型相同， 因此，我们说两者的方法签名一致，
 *      可以直接把方法名作为Lambda表达式传入： * Arrays.sort(array, 类名::cmp); 看代码
 *
 *      2.2 实例方法引用
 *
 *
 * 3.小结
 * FunctionalInterface允许传入：
 *      接口的实现类（传统写法，代码较繁琐）；
 *      Lambda表达式（只需列出参数名，由编译器推断类型）；
 *      符合方法签名的静态方法；
 *      符合方法签名的实例方法（实例类型被看做第一个参数类型）；
 *      符合方法签名的构造方法（实例类型被看做返回类型）。(没举例)
 *
 * FunctionalInterface不强制继承关系，只要求方法签名相同(参数（类型和数量）与返回类型相同, 与方法名无关)
 *
 * @author welldo
 * @date 2020/9/16
 */
public class Lambda2 {
    public static void main(String[] args) {
        String[] array = new String[] { "Apple", "click", "Banana", "dick" };
        // oldTest1(array);
        // newTest1(array);

        //2.
        // test21(array);
        test22(array);
    }


    /**
     * 2.2 实例方法引用
     * 不但可以编译通过，而且运行结果也是一样的，这说明String.compareTo()方法也符合Lambda定义。
     * 观察String.compareTo()的方法定义：public int compareTo(String o) { ... ... }
     *
     * 这个方法的签名只有一个参数，为什么和int Comparator<String>.compare(String, String)能匹配呢？
     *
     * 因为实例方法有一个隐含的this参数，String类的compareTo()方法在实际调用的时候，第一个隐含参数总是传入this，相当于静态方法：
     * public static int compareTo(this, String o);
     *
     * 所以，String.compareTo()方法也可作为方法引用传入。
     */
    static void test22(String[] array){
        Arrays.sort(array,String::compareTo);
        System.out.println(String.join(", ", array));
    }


    //2.1 静态方法引用
    static void test21(String[] array){
        Arrays.sort(array,Lambda2::cmp);
        System.out.println(String.join(", ", array));
    }

    static int cmp(String s1, String s2) {
        return s1.compareTo(s2);
    }


    /**
     * 1.老式写法:接口的实现类
     * 以Comparator为例，我们想要调用Arrays.sort()时，可以传入一个Comparator实例，以匿名类方式编写如下：
     */
    static void oldTest1(String[] array){
        Arrays.sort(array, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println(String.join(", ", array));
    }

    /**
     * 1.新写法: Lambda表达式
     * 上述写法非常繁琐。从Java 8开始，我们可以用Lambda表达式替换单方法接口。改写上述代码如下：
     *
     * 观察Lambda表达式的写法，它只需要写出方法定义：
     * (s1, s2) -> {
     *     return s1.compareTo(s2);
     * }
     *
     * 如果只有一行return xxx的代码，完全可以用更简单的写法： (s1, s2) -> s1.compareTo(s2);
     */
    static void newTest1(String[] array){
        Arrays.sort(array, (s1, s2) -> {
            return s1.compareTo(s2);
        });
        System.out.println(String.join(", ", array));
    }

}
