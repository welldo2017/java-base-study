package com.welldo.zero.a20_functional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 使用 filter (和scala的filter一样)
 *
 * 0.
 * filter()操作，就是对一个Stream的所有元素一一进行测试，不满足条件的就被“滤掉”了，剩下的满足条件的元素就构成了一个新的Stream。
 *
 * 例如，我们对1，2，3，4，5这个Stream调用filter()，传入的测试函数f(x) = x % 2 != 0用来判断元素是否是奇数，
 * 这样就过滤掉偶数，只剩下奇数，因此我们得到了另一个序列1，3，5：
 *
 *             f(x) = x % 2 != 0
 *                   │
 *                   │
 *   ┌───┬───┬───┬───┼───┬───┬───┬───┐
 *   │   │   │   │   │   │   │   │   │
 *   ▼   ▼   ▼   ▼   ▼   ▼   ▼   ▼   ▼
 *
 * [ 1   2   3   4   5   6   7   8   9 ]
 *
 *   │   X   │   X   │   X   │   X   │
 *   │       │       │       │       │
 *   ▼       ▼       ▼       ▼       ▼
 *
 * [ 1       3       5       7       9 ]
 *
 *
 * @author welldo
 * @date 2020/9/16
 */
public class StreamFilter6 {
    public static void main(String[] args) {
        // test1();
        test2();
    }

    //从一组给定的LocalDate中过滤掉工作日，以便得到休息日：
    static void test2() {
        Stream.generate(new LocalDateSupplier())
                .limit(60)
                .filter(ldt -> ldt.getDayOfWeek() == DayOfWeek.SATURDAY || ldt.getDayOfWeek() == DayOfWeek.SUNDAY)
                .forEach(System.out::println);
    }


    /**
     * filter()方法接收的对象是Predicate接口对象，它定义了一个test()方法，负责判断元素是否符合条件：
     *
     */
    static void test1(){
        IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .filter(n -> n % 2 != 0)
                .forEach(System.out::println);
    }
}


class LocalDateSupplier implements Supplier<LocalDate> {
    LocalDate start = LocalDate.of(2020, 1, 1);
    int n = -1;
    public LocalDate get() {
        n++;
        return start.plusDays(n);
    }
}