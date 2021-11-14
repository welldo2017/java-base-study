package com.welldo.zero.a4_oop.base;

/**
 * 构造器
 *
 * 1.默认构造方法
 * 是不是任何class都有构造方法？是的。
 * 如果一个类, 程序员没有定义构造方法，编译器会自动为我们生成一个默认构造方法，它没有参数，也没有执行语句，类似这样：
 * class Person {
 *     public Person() {
 *     }
 * }
 *
 * 2. 要特别注意的是，如果程序员自定义了一个构造方法，那么，编译器就不再自动创建默认构造方法：
 * 调用的时候,就会编译错误
 *
 *
 * 3. 多构造方法
 * 一个构造方法可以调用其他构造方法，这样做的目的是便于代码复用。调用其他构造方法的语法是this(…)：
 *
 *
 * @author welldo
 * @date 2020/8/8
 */
public class Constructor2 {
    public static void main(String[] args) {



    }

}

class Person2 {
    private String name;
    private int age;

    //构造方法的名称就是类名。
    //构造方法没有返回值（也没有void）
    //调用构造方法，必须用new操作符。
    public Person2(String name, int age) {
        this.name = name;
        this.age = age;
    }

    //3. 多构造方法
    public Person2(String name) {
        this(name, 18); // 调用另一个构造方法Person2(String, int)
    }

    //3. 多构造方法
    public Person2() {
        this("Unnamed"); // 调用另一个构造方法Person2(String)
    }


    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }
}