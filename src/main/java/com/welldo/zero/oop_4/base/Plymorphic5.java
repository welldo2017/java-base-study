package com.welldo.zero.oop_4.base;

/**
 * 多态性
 * 在继承关系中，子类如果定义了一个与父类方法签名完全相同的方法，被称为覆写（Override）
 *      方法签名 = 方法的名称 + 参数类型。  与 返回值、修饰符 以及 异常 无关
 *
 * 多态的特性就是，运行期才能动态决定调用的子类方法。(动态绑定)
 *
 *
 * @author welldo
 * @date 2020/8/8
 */
public class Plymorphic5 {
    public static void main(String[] args) {

        /*
        一个实际类型为Student，引用类型为Person的变量，调用其run()方法，
        应该打印Person.run还是Student.run?
        Java的实例方法调用, 是基于运行时的实际类型的动态调用，而非变量的声明类型。
         */
        Person5 p = new Student5();
        p.run(); //

    }


    /*
    假设我们编写这样一个方法：
    它传入的参数类型是Person，我们是无法知道传入的参数实际类型究竟是Person，还是Student，还是Person的其他子类，
    因此，也无法确定调用的是不是Person类定义的run()方法。

    所以，多态的特性就是，运行期才能动态决定调用的子类方法。
     */
    public void runTwice(Person5 p) {
        p.run();
        p.run();
    }


}


class Person5 {
    public void run() {
        System.out.println("Person.run");
    }
}

class Student5 extends Person5 {

    //方法重写
    // @Override不是必需的。加上@Override可以让编译器帮助检查是否进行了正确的覆写。
    @Override
    public void run() {
        System.out.println("Student.run");
    }







    //方法重载, 两同一不同, 同一个类种,方法名相同, 参数列表不同. 与返回值无关.
    //注意：方法名相同，方法参数相同，但返回值不同, 这种情况,编译报错
    public String run2() {
        System.out.println("Student.run");
        return  "sss";
    }

    //方法重载, 两同一不同, 同一个类种,方法名相同, 参数列表不同. 与返回值无关.
    //注意：方法名相同，方法参数相同，但返回值不同, 这种情况,编译报错
    public void run2(String name) {
        System.out.println("Student.run");
    }
}