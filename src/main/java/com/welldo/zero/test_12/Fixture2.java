package com.welldo.zero.test_12;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 使用Fixture
 *
 *
 * 0.what
 * 在测试的时候，我们经常遇到一个对象需要初始化，测试完可能还需要清理的情况。
 * 如果每个@Test方法都写一遍这样的重复代码，显然比较麻烦。
 * JUnit提供了编写测试前准备、测试后清理的固定代码，我们称之为Fixture。
 *
 * 通过@BeforeEach来初始化，通过@AfterEach来清理资源：junit5
 * 通过@Before来初始化，通过@After来清理资源：junit4
 *
 * 1.
 * 因为@BeforeAll和@AfterAll在所有@Test方法运行前后仅运行一次，因此，它们只能初始化静态变量，
 * 事实上，@BeforeAll和@AfterAll也只能标注在静态方法上。
 * junit5.@BeforeAll和@AfterAll
 * junit4.@BeforeClass和@AfterClass
 *
 *
 * 2.因此，我们总结出编写Fixture的套路如下：
 * 对于实例变量，在@BeforeEach中初始化，在@AfterEach中清理，它们在各个@Test方法中互不影响，因为是不同的实例；
 * 对于静态变量，在@BeforeAll中初始化，在@AfterAll中清理，它们在各个@Test方法中均是唯一实例，会影响各个@Test方法。
 *
 * @author welldo
 * @date 2020/9/1
 */
public class Fixture2 {
    Calculator calculator;

    @Before
    public void setUp() {
        this.calculator = new Calculator();
    }

    @After
    public void tearDown() {
        this.calculator = null;
    }

    @Test
    public void testAdd() {
        assertEquals(100, this.calculator.add(100));
        assertEquals(150, this.calculator.add(50));
        assertEquals(130, this.calculator.add(-20));
    }

    @Test
    public void testSub() {
        assertEquals(-100, this.calculator.sub(100));
        assertEquals(-150, this.calculator.sub(50));
        assertEquals(-130, this.calculator.sub(-20));
    }
}


class Calculator {
    private long n = 0;

    public long add(long x) {
        n = n + x;
        return n;
    }

    public long sub(long x) {
        n = n - x;
        return n;
    }
}