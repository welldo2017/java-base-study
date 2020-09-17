package com.welldo.zero.functional_20;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 使用map    (和scala的map一样)
 *
 * 0.Stream.map()是Stream最常用的一个转换方法，它把一个Stream转换为另一个Stream。
 * 所谓map操作，就是把一种操作运算，映射到一个序列的每一个元素上。
 * 例如，对x计算它的平方，可以使用函数f(x) = x * x。
 *
 * 我们把 f(x) = x * x 这个函数映射到一个序列1，2，3，4，5上，就得到了另一个序列1，4，9，16，25
 *
 *              f(x) = x * x
 *                   │
 *                   │
 *   ┌───┬───┬───┬───┼───┬───┬───┬───┐
 *   │   │   │   │   │   │   │   │   │
 *   ▼   ▼   ▼   ▼   ▼   ▼   ▼   ▼   ▼
 *
 * [ 1   2   3   4   5   6   7   8   9 ]
 *
 *   │   │   │   │   │   │   │   │   │
 *   │   │   │   │   │   │   │   │   │
 *   ▼   ▼   ▼   ▼   ▼   ▼   ▼   ▼   ▼
 *
 * [ 1   4   9  16  25  36  49  64  81 ]
 *
 * @author welldo
 * @date 2020/9/16
 */
public class StreamMap5 {
    public static void main(String[] args) {
        // test1();
        test2();
    }

    //2. 利用map()，不但能完成数学计算，对于字符串操作，以及任何Java对象都是非常有用的。
    static void test2() {
        String[] strs = {"  Apple ", " pear ", " ORANGE", " BaNaNa "};
        List<String> list = Arrays.asList(strs);
        list.stream()
                .map(String::trim) // 去空格
                .map(String::toLowerCase) // 变小写
                .forEach(System.out::println); // 打印
    }

    /**
     * 1. 查看map()源码，会发现此方法接收的对象是Function接口对象，它定义了一个apply()方法，负责把一个T类型转换成R类型：
     */
    static void test1(){
        Stream<Integer> s = Stream.of(1, 2, 3, 4, 5);
        Stream<Integer> s2 = s.map(n -> n * n);
        s2.forEach(System.out::println);
    }

}
