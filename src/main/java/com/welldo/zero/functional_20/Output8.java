package com.welldo.zero.functional_20;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * 输出到集合
 *
 * Stream的几个常见操作：map()、filter()、reduce()。可以分为两类，
 * 一类是转换操作，即把一个Stream转换为另一个Stream，例如map()和filter()，
 * 另一类是聚合操作，即对Stream的每个元素进行计算，得到一个确定的结果，例如reduce()。
 *
 * 1.
 * 区分这两种操作是非常重要的，因为对于Stream来说，对其进行转换操作并不会触发任何计算！
 *
 *
 * @author welldo
 * @date 2020/9/16
 */
public class Output8 {

    public static void main(String[] args) {

    }

    static void test1(){
        Stream<Long> s1 = Stream.generate(new NatualSupplier8());
        Stream<Long> s2 = s1.map(n -> n * n);
        Stream<Long> s3 = s2.map(n -> n - 1);
        Stream<Long> s4 = s3.limit(10);
        s4.reduce(0, (acc, n) -> acc + n);
    }


}
class NatualSupplier8 implements Supplier<Long> {
    long n = 0;
    public Long get() {
        n++;
        return n;
    }
}
