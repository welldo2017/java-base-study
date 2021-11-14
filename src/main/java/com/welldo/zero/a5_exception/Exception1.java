package com.welldo.zero.a5_exception;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * Java的异常
 *
 *
 * 因为Java的异常是class，它的继承关系如下：
 *
 *                      ┌───────────┐
 *                      │  Object   │
 *                      └───────────┘
 *                            ▲
 *                            │
 *                      ┌───────────┐
 *                      │ Throwable │
 *                      └───────────┘
 *                            ▲
 *                  ┌─────────┴─────────┐
 *                  │                   │
 *            ┌───────────┐       ┌───────────┐
 *            │   Error   │       │ Exception │
 *            └───────────┘       └───────────┘
 *                  ▲                   ▲
 *          ┌───────┘              ┌────┴──────────┐
 *          │                      │               │
 * ┌─────────────────┐    ┌─────────────────┐┌───────────┐
 * │OutOfMemoryError │... │RuntimeException ││IOException│...
 * └─────────────────┘    └─────────────────┘└───────────┘
 *                                 ▲
 *                     ┌───────────┴─────────────┐
 *                     │                         │
 *          ┌─────────────────────┐ ┌─────────────────────────┐
 *          │NullPointerException │ │IllegalArgumentException │...
 *          └─────────────────────┘ └─────────────────────────┘
 *
 *
 * Error表示严重的错误，程序对此一般无能为力，例如：(不需要捕获)
 *      OutOfMemoryError：内存耗尽
 *      NoClassDefFoundError：无法加载某个Class
 *      StackOverflowError：栈溢出
 *
 * 而 Exception 则是运行时的错误，它可以被捕获并处理。
 *      某些异常是应用程序逻辑处理的一部分，应该捕获并处理
 *      还有一些异常是程序逻辑编写不对造成的，应该修复程序本身
 *
 *
 * Exception又分为两大类：
 *       RuntimeException以及它的子类；(不做强制捕获要求, 是否需要捕获，具体问题具体分析)
 *       非RuntimeException , 非运行时异常, 也就是编译时异常(必须捕获)
 *
 *
 * !!! 只要是方法声明的Checked Exception，不在调用层捕获，也必须在更高的调用层捕获。
 * 所有未捕获的异常，最终也必须在main()方法中捕获，不会出现漏写try的情况。
 * 这是由编译器保证的。main()方法也是最后捕获Exception的机会。 *
 *
 *
 * 因为处理 aException 和 bException 的代码是相同的，所以我们可以把它两用|合并到一起：
 *  catch (IOException | NumberFormatException e) {
 *         System.out.println("Bad input");
 *     }
 *
 *
 * @author welldo
 * @date 2020/8/11
 */
public class Exception1 {

    public static void main(String[] args) {
        byte[] bs = toGBK("中文");

        System.out.println(Arrays.toString(bs));
    }

    static byte[] toGBK(String s) {
        try {
            // 用指定编码转换String为byte[]:
            return s.getBytes("GBK");//[-42, -48, -50, -60]

        } catch (UnsupportedEncodingException e) {
            // 如果系统不支持GBK编码，会捕获到UnsupportedEncodingException:
            System.out.println(e); // 打印异常信息
            return s.getBytes(); // 尝试使用用默认编码
        }
    }



    static void process2() {
        Integer.parseInt(null); // 会抛出NumberFormatException
    }


}
