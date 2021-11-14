package com.welldo.zero.a1_data_type;

/**
 * 变量
 *
 * 在Java中，变量分为两种：基本类型的变量和引用类型的变量。
 * 我们先讨论基本类型的变量。
 *
 * 我们一行一行地分析代码执行流程：
 * 执行int n = 100;，该语句定义了变量n，同时赋值为100，因此，JVM在内存中为变量n分配一个“存储单元”，填入值100：
 *       n
 *       │
 *       ▼
 * ┌───┬───┬───┬───┬───┬───┬───┐
 * │   │100│   │   │   │   │   │
 * └───┴───┴───┴───┴───┴───┴───┘
 *
 * 执行n = 200;时，JVM把200写入变量n的存储单元，因此，原有的值被覆盖，现在n的值为200：
 *       n
 *       │
 *       ▼
 * ┌───┬───┬───┬───┬───┬───┬───┐
 * │   │200│   │   │   │   │   │
 * └───┴───┴───┴───┴───┴───┴───┘
 *
 * 执行int x = n;时，定义了一个新的变量x，同时对x赋值，因此，JVM需要新分配一个存储单元给变量x，并写入和变量n一样的值，结果是变量x的值也变为200：
 *       n           x
 *       │           │
 *       ▼           ▼
 * ┌───┬───┬───┬───┬───┬───┬───┐
 * │   │200│   │   │200│   │   │
 * └───┴───┴───┴───┴───┴───┴───┘
 *
 * 执行x = x + 100;时，JVM首先计算等式右边的值x + 100，结果为300（因为此刻x的值为200），
 * 然后，将结果300写入x的存储单元，因此，变量x最终的值变为300：
 *       n           x
 *       │           │
 *       ▼           ▼
 * ┌───┬───┬───┬───┬───┬───┬───┐
 * │   │200│   │   │300│   │   │
 * └───┴───┴───┴───┴───┴───┴───┘
 * 可见，变量可以反复赋值。注意，等号=是赋值语句，不是数学意义上的相等，否则无法解释x = x + 100。
 *
 *
 *
 * author:welldo
 * date: 2020/12/9 15:52
 */
public class Variable0 {
}