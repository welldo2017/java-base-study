package com.welldo.zero.a12_test;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 0.what
 * 单元测试就是针对最小的 "功能单元" 编写测试代码。Java程序最小的功能单元是方法，因此，对Java中, 就是针对单个方法的测试。
 *
 *
 * JUnit
 * JUnit是一个开源的Java语言的单元测试框架，专门针对Java设计，使用最广泛。
 * JUnit是事实上的标准，任何Java开发者都应当学习并使用JUnit编写单元测试。
 *
 * 使用JUnit编写单元测试的好处在于，我们可以非常简单地组织测试代码，并随时运行它们，
 * JUnit就会给出成功的测试和失败的测试，还可以生成测试报告，不仅包含测试的成功率，还可以统计测试的代码覆盖率，即被测试的代码本身有多少经过了测试。
 * 对于高质量的代码来说，测试覆盖率应该在80%以上。
 *
 *
 * 1.how
 * 在测试方法内部，我们用assertEquals(1, Factorial.fact(1))表示，期望Factorial.fact(1)返回1。
 * assertEquals(expected, actual)是最常用的测试方法，它在Assertion类中定义。
 * Assertion还定义了其他断言方法，例如：
 *      assertTrue(): 期待结果为true
 *      assertFalse(): 期待结果为false
 *      assertNotNull(): 期待结果为非null
 *      assertArrayEquals(): 期待结果为数组并与期望数组每个元素的值均相等
 *      ...
 *
 * @author welldo
 * @date 2020/9/1
 */
public class What1 {

    //1.
    @Test
    public void testFact() {
        assertEquals(1, Factorial.fact(1));
        assertEquals(2, Factorial.fact(2));
        assertEquals(6, Factorial.fact(3));
        assertEquals(3628800, Factorial.fact(10));
        assertEquals(2432902008176640000L, Factorial.fact(20));
    }
}


class Factorial {
    /**
     * 计算阶乘
     * n!=1×2×3×...×n
     */
    public static long fact(long n) {
        long r = 1;
        for (long i = 1; i <= n; i++) {
            r = r * i;
        }
        return r;
    }
}