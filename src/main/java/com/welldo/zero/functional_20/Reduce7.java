package com.welldo.zero.functional_20;

import java.util.*;
import java.util.stream.Stream;

/**
 * 使用reduce
 *
 * map()和filter()都是Stream的转换方法，
 * Stream.reduce()则是Stream的一个聚合方法，它可以把一个Stream的所有元素按照"聚合函数"聚合成一个结果。
 *
 * @author welldo
 * @date 2020/9/16
 */
public class Reduce7 {
    public static void main(String[] args) {
        test1();
        test2();
    }

    /**
     * 活运用reduce()也可以对Java对象进行操作。
     * 下面的代码演示了如何将配置文件的每一行配置通过map()和reduce()操作聚合成一个Map<String, String>：
     */
    static void test2(){
        // 按行读取配置文件:
        String[] strs = {"profile=native", "debug=true", "logging=warn", "interval=500"};
        List<String> props = Arrays.asList(strs);

        Map<String, String> map = props.stream()
                // 把k=v转换为Map[k]=v:
                .map(kv -> {
                    String[] ss = kv.split("\\=", 2);
                    // return new HashMap<String, String>().put(ss[0], ss[1]);
                    return Collections.singletonMap(ss[0], ss[1]);
                })
                // 把所有Map聚合到一个Map:
                .reduce(new HashMap<String, String>(), (m, kv) -> {
                    m.putAll(kv);
                    return m;
                });

        // 打印结果:
        map.forEach((k, v) -> {
            System.out.println(k + " = " + v);
        });
    }


    /**
     * reduce()方法传入的对象是BinaryOperator接口，它定义了一个apply()方法，
     * 负责把上次累加的结果和本次的元素 进行运算，并返回累加的结果：
     *
     * public interface BiFunction<T, U, R> {
     *      R apply(T t, U u);
     * }
     *
     * reduce()操作首先初始化原始值（这里是0），紧接着，reduce()对每个元素依次调用(acc, n) -> acc + n，其中，acc是上次计算的结果：
     * 计算过程:
     * acc = 0                  // 初始化为指定值
     * acc = acc + n = 0 + 1 = 1 // n = 1
     * acc = acc + n = 1 + 2 = 3 // n = 2
     * acc = acc + n = 3 + 3 = 6 // n = 3
     * acc = acc + n = 6 + 4 = 10 // n = 4
     * acc = acc + n = 10 + 5 = 15 // n = 5
     * acc = acc + n = 15 + 6 = 21 // n = 6
     * acc = acc + n = 21 + 7 = 28 // n = 7
     * acc = acc + n = 28 + 8 = 36 // n = 8
     * acc = acc + n = 36 + 9 = 45 // n = 9
     */
    static void test1(){
        //有初始值
        int sum = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .reduce(0, (acc, n) -> acc + n);//这个reduce()操作是一个求和。
        System.out.println(sum); // 45

        // 如果去掉初始值，我们会得到一个Optional<Integer>：
        //这是因为Stream的元素有可能是0个，这样就没法调用reduce()的聚合函数了，因此返回Optional对象，需要进一步判断结果是否存在。
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Optional<Integer> optional = integerStream.reduce((acc, n) -> acc + n);
        if (optional.isPresent()) {
            System.out.println(optional.get());
        }

        //求乘积
        int s = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .reduce(1, (acc, n) -> acc * n);
        System.out.println(s); // 362880
    }
}
