package com.welldo.zero.a11_io;

/**
 * PrintStream和PrintWriter
 *
 * 1.PrintStream
 * 它是一种 FilterOutputStream，(filter模式, 请参考 {@link Filter6})
 * 它在OutputStream 类上(只能写入字节/字节数组)，额外提供了一些写入各种数据类型的方法：
 *
 * 我们经常使用的System.out.println()实际上就是使用PrintStream打印各种数据。
 * 其中，System.out是系统默认提供的PrintStream，表示标准输出：
 * System.err是系统默认提供的标准错误输出。
 *
 *
 * @author welldo
 * @date 2020/8/29
 */
public class PrintStream11 {
    public static void main(String[] args) {

        //1.
        System.out.print(12345); // 输出12345
        System.out.println(new Object()); // 输出类似java.lang.Object@3c7a835a
        System.out.println("Hello"); // 输出Hello并换行
        System.err.println("Hello-err"); // 输出Hello并换行
    }
}
