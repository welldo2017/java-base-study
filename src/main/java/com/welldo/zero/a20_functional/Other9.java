package com.welldo.zero.a20_functional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 其他操作
 * Stream提供的常用操作有：
 *      转换操作：map()，filter()，sorted()，distinct()；
 *      合并操作：concat()，flatMap()；
 *      并行处理：parallel()；
 *      聚合操作：reduce()，collect()，count()，max()，min()
 *          针对IntStream、LongStream和DoubleStream，还额外提供了以下聚合方法：
 *          sum()：对所有元素求和；
 *          average()：对所有元素求平均数。
 *      其他操作：
 *          allMatch()测试是否所有元素均满足测试条件
 *          anyMatch()测试是否至少有一个元素满足测试条件。
 *          forEach()
 *
 *
 * @author welldo
 * @date 2020/9/17
 */
public class Other9 {
    public static void main(String[] args) {
        // test1();
        // test2();
        // test3();
        // test4();
        // test5();
        test6();
    }

    /**
     * 并行
     * 通常情况下，对Stream的元素进行处理是单线程的，即一个一个元素进行处理。
     * 但是很多时候，我们希望可以并行处理Stream的元素，因为在元素数量非常大的情况，并行处理可以大大加快处理速度。
     */
    static void test6(){
        //对<悲惨世界-非完整版>进行排序, 10.0M 大小, 一共10w行
        long start = System.currentTimeMillis();
        try (Stream<String> Miserables = Files.lines(Paths.get("io/Les Miserables.txt"))) {
            List<String> list = Miserables
                    // .parallel()  //文件太小的情况下, 并行否与没有多大差别
                    .filter(s -> s != null && !s.trim().isEmpty() )
                    .map(s -> s.substring(0, 1))
                    .sorted(String::compareToIgnoreCase)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }

    /**
     * flatMap
     * 把 Stream<List<Integer>> 转换为Stream<Integer>，就可以使用flatMap()：
     */
    static void test5(){
        Stream<List<Integer>> s = Stream.of(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5, 6),
                Arrays.asList(7, 8, 9)
        );
        Stream<Integer> integerStream = s.flatMap(list -> list.stream());

        integerStream.forEach(System.out::println);
    }


    //合并. 将两个Stream合并为一个Stream可以使用Stream的静态方法concat()：
    static void test4(){
        String[] array1= {"A", "B"};
        List<String> list1 = Arrays.asList(array1);

        String[] array2= {"C", "D", "E"};
        List<String> list2 = Arrays.asList(array2);

        Stream<String> stream1 = list1.stream();
        Stream<String> stream2 = list2.stream();

        Stream<String> concat = Stream.concat(stream1, stream2);
        System.out.println(concat.collect(Collectors.toList()));
    }


    /**
     * 截取操作常用于把一个无限的Stream转换成有限的Stream，
     * skip()用于跳过当前Stream的前N个元素，limit()用于截取当前Stream最多前N个元素：
     */
    static void test3(){
        String[] array= {"A", "B", "C", "D", "E", "F"};
        List<String> list = Arrays.asList(array);

        List<String> collect = list
                .stream()
                .skip(2) // 跳过A, B
                .limit(3) // 截取C, D, E
                .collect(Collectors.toList()); // [C, D, E]
        System.out.println(collect);
    }



    //去重, 对一个Stream的元素进行去重，没必要先转换为Set，可以直接用distinct()：
    static void test2(){
        String[] array= {"A", "B", "A", "C", "B", "D"};
        List<String> list = Arrays.asList(array);

        List<String> collect = list.stream().distinct().collect(Collectors.toList());
        System.out.println(collect);
    }



    //排序
    static void test1(){
        String[] array= {"click", "apple", "Banana"};
        List<String> list = Arrays.asList(array);

        //未指定排序方式
        //todo
        List<String> collect = list.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(collect);

        //此方法要求Stream的每个元素必须实现Comparable接口。如果要自定义排序，传入指定的Comparator即可：
        //todo
        List<String> collect2 = list.stream()
                .sorted(String::compareToIgnoreCase)
                .collect(Collectors.toList());
        System.out.println(collect2);
    }
}

