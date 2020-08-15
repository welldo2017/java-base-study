package com.welldo.zero.exception5;

/**
 * NullPointerException 空指针异常
 *
 * 1.
 * 如果一个对象为null，调用其方法或访问其字段就会产生NullPointerException，这个异常通常是由JVM抛出的，例如：
 * String s = null;
 * System.out.println(s.toLowerCase());
 *
 * 2.
 * 指针这个概念实际上源自C语言，Java语言中并无指针。
 * 我们定义的变量实际上是引用，Null Pointer更确切地说是Null Reference,空引用。
 *.
 * 3.
 * 首先，必须明确，NullPointerException是一种代码逻辑错误，遇到NullPointerException，遵循原则是早暴露，早修复，
 * 好的编码习惯可以极大地降低NullPointerException的产生，例如：
 * 返回空字符串""、空数组而不是null：
 * 并且, 这样可以使得调用方无需检查结果是否为null。
 *
 *
 *
 * @author welldo
 * @date 2020/8/12
 */
public class NullPointerException4 {

}
