package com.welldo.zero.functional_20;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * 创建Stream
 *
 * 0.要使用Stream，就必须现创建它。创建Stream有很多种方法，我们来一一介绍。
 *
 * 1.Stream.of()
 *
 * 2.基于array或Collection
 *
 * 3.基于Supplier
 * 创建Stream还可以通过Stream.generate()方法，它需要传入一个Supplier对象：
 * Stream<T> s = Stream.generate(Supplier<T> sp);
 *
 * 基于Supplier创建的Stream会不断调用Supplier.get()方法来不断产生下一个元素，
 * 这种Stream保存的不是元素，而是算法，它可以用来表示无限序列。
 *
 *
 *
 * 4.其他方法
 * 通过一些API提供的接口，直接获得Stream。
 * 例如，Files类的lines()方法可以把一个文件变成一个Stream，每个元素代表文件的一行内容：
 * try (Stream<String> lines = Files.lines(Paths.get("/path/to/file.txt"))) {
 *     ...
 * }
 * 此方法对于按行遍历文本文件十分有用。
 *
 *
 * 5.基本类型
 * 因为Java的泛型不支持基本类型，所以我们无法用Stream<int>这样的类型，会发生编译错误。
 * 为了保存int，只能使用Stream<Integer>，但这样会产生频繁的装箱、拆箱操作。
 * 为了提高效率，Java标准库提供了IntStream、LongStream和DoubleStream这三种使用基本类型的Stream，
 * 它们的使用方法和范型Stream没有大的区别，
 * 设计这三个Stream的目的是提高运行效率：
 *
 *
 * @author welldo
 * @date 2020/9/16
 */
public class Stream4 {
    public static void main(String[] args) {
        // test1();
        // test2();
        test3();
    }


    static void test5(){
        // 将int[]数组变为IntStream:
        int[] ints = {1,2,3,4};
        IntStream is = Arrays.stream(ints);

        // 将Stream<String>转换为LongStream:
        String[] strings = {"1", "2", "3"};
        List<String> list = Arrays.asList(strings);
        LongStream ls = list.stream().mapToLong(Long::parseLong);
    }


    /**
     * 3.基于Supplier
     */
    static void test3(){
        Stream<Integer> integerStream = Stream.generate(new NatualSupplier());
        // 注意：无限序列必须先变成有限序列再打印:
        //对于无限序列，如果直接调用forEach()或者count()这些最终求值操作，会进入死循环，因为永远无法计算完这个序列
        //limit(n), NatualSupplier.get()方法就会调用n次
        integerStream.limit(20).forEach(System.out::println);
    }


    /**
     * 2.基于array或Collection
     *
     * 把数组变成Stream使用Arrays.stream()方法。
     * 对于Collection（List、Set、Queue等），直接调用stream()方法就可以获得Stream。
     * 上述创建Stream的方法都是把一个现有的序列变为Stream，它的元素是固定的
     */
    static void test2(){
        String[] strings = {"A", "B", "C"};

        Stream<String> stream1 = Arrays.stream(strings);
        stream1.forEach(System.out::println);

        List<String> list = Arrays.asList(strings);
        Stream<String> stream2 = list.stream();
        stream2.forEach(System.out::println);
    }

    /**
     * 1.Stream.of()
     * 这种方式基本上没啥实质性用途，但测试的时候很方便。
     *
     * 注意: 对stream执行一次操作后,
     * 再执行第二次会抛异常: stream has already been operated upon or closed
     */
    static void test1(){
        Stream<String> stream = Stream.of("A", "B", "C", "D", "e", "f");
        /*
        forEach()方法
        void forEach(Consumer<? super T> action)方法
        源码解释: Performs an action for each element 对每个元素都执行 action

        action是什么: 是实现Consumer接口中的accept()方法的自定义类
        也可传入符合Consumer接口的void accept(T t)的方法引用：
         */
        stream.forEach(n -> System.out.println(n));//lambda写法

        //可以简写成如下:
        // stream.forEach(System.out::println);//静态方法引用
    }
}


/**
 * 3. 编写一个能不断生成自然数的Supplier，它的代码非常简单，每次调用get()方法，就生成下一个自然数：
 *
 * 我们用一个Supplier<Integer>模拟了一个无限序列（当然受int范围限制不是真的无限大）。
 * 如果用List表示，即便在int范围内，也会占用巨大的内存，
 * 而Stream几乎不占用空间，因为每个元素都是实时计算出来的，用的时候再算。
 */
class NatualSupplier implements Supplier<Integer> {
    int n = 0;

    public Integer get() {
        n++;
        System.out.println(n);
        return n;
    }
}




