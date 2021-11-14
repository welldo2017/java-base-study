package com.welldo.zero.a20_functional;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 输出到集合
 *
 * Stream的几个常见操作：map()、filter()、reduce()。可以分为两类，
 * 一类是转换操作，即把一个Stream转换为另一个Stream，例如map()和filter()，
 * 另一类是聚合操作，即对Stream的每个元素进行计算，得到一个确定的结果，例如reduce()。
 *
 * 1. * 区分这两种操作是非常重要的，因为对于Stream来说，无论对其进行多少次转换操作,都不会触发任何实际计算,看代码
 *
 * 2.聚合操作会立刻使Stream输出它的每一个元素，并依次纳入计算，以获得最终结果。
 * 所以，对一个Stream进行聚合操作，会触发一系列连锁反应：
 *
 *
 * 3.输出为List
 * 如果我们希望把Stream的元素保存到集合，例如List，因为List的元素是确定的Java对象，
 * 因此，把Stream变为List不是一个转换操作，而是一个聚合操作，它会强制Stream输出每个元素。
 *
 *  collect( Collectors.toList() )  通过类似reduce()的操作，把每个元素添加到一个收集器中（实际上是ArrayList）。
 *  类似的，collect(Collectors.toSet())可以把Stream的每个元素收集到Set中。
 *  类似的，我们只需要调用toArray()方法，并传入数组的“构造方法”, 可以输出为数组
 *
 *
 * 4.输出为Map
 * 如果我们要把Stream的元素收集到Map中，就稍微麻烦一点。
 * 因为对于每个元素，添加到Map时需要key和value，因此，我们要指定两个映射函数，分别把元素映射为key和value：
 *
 *
 * 5.分组输出
 *
 *
 * @author welldo
 * @date 2020/9/16
 */
public class Output8 {

    public static void main(String[] args) {
        // test1();
        // test2();
        test3();
        test4();
        test5();
    }

    /**
     * 分组输出使用Collectors.groupingBy()，它需要提供两个函数：
     * 一个是分组的key，这里使用s -> s.substring(0, 1)，表示只要首字母相同的String分到一组，
     * 第二个是分组的value，这里直接使用Collectors.toList()，表示输出为List，
     */
    static void test5(){
        String[] array= {"Apple", "Banana", "Blackberry", "Coconut", "Avocado", "Cherry", "Apricots"};
        List<String> list = Arrays.asList(array);

        Map<String, List<String>> groups = list.stream()
                .collect(
                        Collectors.groupingBy(
                                s -> s.substring(0, 1),
                                Collectors.toList())
                );
        System.out.println(groups);
    }


    static void test4(){
        Stream<String> stream = Stream.of("APPL:Apple", "MSFT:Microsoft");
        Map<String, String> map = stream
                .collect(Collectors.toMap(
                        // 把元素s映射为key:
                        s -> s.substring(0, s.indexOf(':')),
                        // 把元素s映射为value:
                        s -> s.substring(s.indexOf(':') + 1)));
        System.out.println(map);
    }

    //3.演示了如何将一组String先过滤掉空字符串，然后把非空字符串保存到List中：
    static void test3(){
        Stream<String> stream = Stream.of("Apple", "", null, "Pear", "  ", "Orange");
        List<String> list = stream
                .filter(s -> s != null && !s.trim().isEmpty())
                .collect(Collectors.toList());//输出为list
        System.out.println(list);

        Stream<String> stream2 = Stream.of("Apple","Apple","Apple", "", null, "Pear", "  ", "Orange");
        Set<String> set = stream2.filter(s -> s != null && !s.trim().isEmpty())
                .collect(Collectors.toSet());  //输出为set
        System.out.println(set);

        /*
         * 注意到传入的“构造方法”是String[]::new，
         * 它的签名实际上是IntFunction<String[]>定义的String[] apply(int)，即传入int参数，获得String[]数组的返回值。
         */
        Stream<String> stream3 = Stream.of("Apple","Apple","Apple", "", null, "Pear", "  ", "Orange");
        String[] array = stream3.filter(s -> s != null && !s.trim().isEmpty())
                .toArray(String[]::new);    //传入数组的“构造方法”：
        System.out.println(array[0]);
    }


    static void test2(){
        Stream<Long> s1 = Stream.generate(new NatualSupplier8());
        Stream<Long> s2 = s1.map(n -> n * n);
        Stream<Long> s3 = s2.map(n -> n - 1);
        Stream<Long> s4 = s3.limit(5);
        Long value = s4.reduce(0L, (acc, n) -> acc + n);
        System.out.println(value.longValue());//50
    }

    /**
     * 1.因为s1是一个Long类型的序列，它的元素高达922亿个，但执行代码，
     * 既不会有任何内存增长，也不会有任何计算，因为转换操作只是保存了转换规则
     * ，无论我们对一个Stream转换多少次，都不会有任何实际计算发生。
     */
    static void test1(){
        Stream<Long> s1 = Stream.generate(new NatualSupplier8());
        Stream<Long> s2 = s1.map(n -> n * n);
        Stream<Long> s3 = s2.map(n -> n - 1);
        System.out.println(s3);//java.util.stream.ReferencePipeline$3@15aeb7ab
    }
}


class NatualSupplier8 implements Supplier<Long> {
    long n = 0;
    public Long get() {
        n++;
        return n;
    }
}
