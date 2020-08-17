package com.welldo.zero.Annotation8;

import com.sun.istack.internal.NotNull;

/**
 * 使用注解(Annotation)
 *
 * 1.
 * 注解是放在Java源码的类、方法、字段、参数前的一种特殊“注释”：
 * 注释会被编译器直接忽略，注解则可以被编译器打包进入class文件，因此，注解是一种用作标注的“元数据”。
 *
 * 2.Java的注解可以分为三类：
 * 第一类是由编译器使用的注解，例如： * @Override：@SuppressWarnings;
 *      这类注解不会被编译进入.class文件，它们在编译后就被编译器扔掉了。
 *
 * 第二类是由工具处理.class文件使用的注解;
 *      这类注解只被一些底层库使用，一般我们不必自己处理。
 *
 * 第三类是在程序运行期能够读取的注解，它们在加载后一直存在于JVM中,这也是最常用的注解。
 *
 * @author welldo
 * @date 2020/8/15
 */
public class UseAnnotation1 {

    /**
     * 给注解赋值的方法, 参考 {@link Report2}
     */
    @Report2(min=10, max=50)
    public int n;

    @Report2(value="99")
    public int p;

    @Report2("99") //相当于给value赋值 @Check(value="99")
    public int x;

    @Report2    //相当于全部都用默认值
    public int y;


    public void hello(@NotNull String name,@NotNull String prefix) {    }

}
