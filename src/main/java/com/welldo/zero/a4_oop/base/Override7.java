package com.welldo.zero.a4_oop.base;

/**
 * 覆写Object方法
 * 因为所有的class最终都继承自Object，而Object定义了几个重要的方法：
 * <p>
 * toString()：把instance输出为String；
 * equals()：判断两个instance是否逻辑相等；
 * hashCode()：计算一个instance的哈希值。
 * 在必要的情况下，我们可以覆写Object的这几个方法。例如：
 *
 *
 * final
 * 继承可以允许子类覆写父类的方法。如果一个父类不允许子类对它的某个方法进行覆写，可以把该方法标记为final。
 * 用final修饰的类不能被继承：
 * 用final修饰的字段在初始化后不能被修改。
 *
 *
 * @author welldo
 * @date 2020/8/8
 */
public class Override7 {
    public static void main(String[] args) {
        Person7 person7 = new Person7();
        System.out.println(person7);
    }
}


class Person7 {

    protected String name;

    // 显示更有意义的字符串:
    @Override
    public String toString() {
        return "Person:name=" + name;
    }


    public String hello() {
        return "Hello, " + name;
    }

    //用final修饰的方法不能被Override：
    public final String finalHello() {
        return "finalHello, " + name;
    }
}

class Student7 extends Person7 {

    //在子类的覆写方法中，如果要调用父类的被覆写的方法，可以通过super来调用
    @Override
    public String hello() {
        // 调用父类的hello()方法:
        return super.hello() + "!";
    }
}