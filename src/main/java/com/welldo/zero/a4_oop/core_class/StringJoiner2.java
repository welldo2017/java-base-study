package com.welldo.zero.a4_oop.core_class;

import java.util.StringJoiner;

/**
 * StringJoiner2
 *
 *
 *
 * @author welldo
 * @date 2020/8/10
 */
public class StringJoiner2 {
    public static void main(String[] args) {
        String[] names = {"Bob", "Alice", "Grace"};

        //1. 用分隔符拼接数组的需求很常见，所以Java标准库提供了一个StringJoiner来干这个事
        StringJoiner sj = new StringJoiner(", ");
        for (String name : names) {
            sj.add(name);
        }
        System.out.println(sj.toString());


        //2. 给StringJoiner指定“开头”和“结尾”：
        StringJoiner sjj = new StringJoiner(", ", "hello ", "!");
        for (String name : names) {
            sjj.add(name);
        }
        System.out.println(sjj);


        //3. 在不需要指定“开头”和“结尾”的时候，用String.join()更方便：(此方法内部调用了 StringJoiner )
        String join = String.join(",", names);
        System.out.println(join);
    }
}
