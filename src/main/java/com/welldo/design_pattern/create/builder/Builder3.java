package com.welldo.design_pattern.create.builder;

import java.util.Date;

/**
 * 3.生成器模式（GenericBuilder）
 * 原文:  https://mp.weixin.qq.com/s/QusQPnFHE3jsY8tF9a9i1g
 *
 * 将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。
 * 生成器模式（GenericBuilder）是使用多个“小型”工厂来最终创建出一个完整对象。
 *
 *
 * 1.大量参数构造器的缺点：
 * --参数过多时，代码太长，极不优雅，维护极难；
 * --不能判断出哪些是必须参数，哪些是可选参数，可选参数也得给个默认值；
 * --分不清变量值对应哪个变量，如顺序对应错，很容易造成错误；
 * --构造器参数增减时，会影响所有创建该对象的地方，影响扩展性；
 *
 * 2.构造器正确的用法
 * 只给出几个必选、重要参数的构造器，而不是把所有参数放在一个构造器中。
 *
 * 3.GenericBuilder 模式改良的思路, 见{@link Task3}
 *
 * 4.使用方法,见代码
 *
 * 7. Lombok 中的 GenericBuilder 模式，一个 @GenericBuilder 注解搞定所有(不推荐使用)
 * (反编译后查看源代码,思想和逻辑都是一样的)
 *
 * 8.Java 8 带来了函数式接口编程，所以在 Java 8 中可以一个实现通用的 GenericBuilder：
 *  http://www.ciphermagic.cn/java8-builder.html
 *
 *
 *
 * author:welldo
 * date: 2021/4/26 15:35
 */
public class Builder3 {

    public static void main(String[] args) {
        /*
         * 4. 使用方法：
         * 先创建构造器，然后在每个方法后使用 . 带出所有方法，一目了然，最后调用 build 方法以结束链式调用创建 bean。
         *
         * 5.GenericBuilder 模式的优点：
         * 链式调用，优雅、清晰、一目了然；
         * 一行代码完成对象创建，避免了多行代码赋值过程出错；
         * 省去了大量冗余变量，避免变量复制出错；
         *
         * 6.缺点
         * 只适合一次赋值创建对象，多次赋值的场景还需要新增 set 方法配合，不是很灵活；
         */
        Task3 tast = new Task3.TaskBuilder(1, "task1")
                .type(1)
                .status(2)
                .content("紧急任务")
                .finishDate(new Date())
                .build();
        System.out.println(tast);


    }

}
