package com.welldo.zero.oop_4.base;

/**
 *
 * 抽象类
 * 如果父类的方法本身不需要实现任何功能，仅仅是为了定义方法签名，目的是让子类去覆写它，那么，可以把父类的方法声明为抽象方法：
 *
 *
 * 包含abstract 方法, 因此这个类也必须申明为抽象类（abstract class）
 * 我们无法实例化一个抽象类： * 无法实例化的抽象类有什么用？
 * 因为抽象类本身被设计成只能用于被继承，因此，抽象类可以强迫子类实现其定义的抽象方法，否则编译会报错。
 * 因此，抽象方法实际上相当于定义了“规范”。
 *
 *
 * 面向抽象编程的本质就是：
 * 上层代码只定义规范（例如：abstract class Person）；
 * 不需要子类就可以实现业务逻辑（正常编译）；
 * 具体的业务逻辑由不同的子类实现，调用者并不关心。
 *
 *
 * @author welldo
 * @date 2020/8/8
 */
public class Abstract8 {
    public static void main(String[] args) {
        Person8 p = new Student8();
        p.run();
    }
}



//
abstract class Person8 {
    protected String name;

    //把一个方法声明为abstract，表示它是一个抽象方法，本身没有实现任何方法语句。因为这个抽象方法本身是无法执行的，
    public abstract void run();
}

class Student8 extends Person8 {
    @Override
    public void run() {
        System.out.println("Student.run");
    }
}