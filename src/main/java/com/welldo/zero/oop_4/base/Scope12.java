package com.welldo.zero.oop_4.base;

/**
 * 作用域 *
 * private , default(也就是什么都不写, 也就是包作用域) ,protected ,public
 *
 * 1. class 只能被public和default 修饰.
 *
 *
 * ********************************************************************
 * private
 * 定义为private的 field、method 无法被其他类访问：
 *
 * package abc;
 * public class Hello {
 *     // 不能被其他类调用:
 *     private void hi() { *     }
 *
 *     public void hello() {
 *         this.hi();// 只能在class内部被调用
 *     }
 * }
 * private访问权限被限定在class的内部。推荐把private方法放到后面，因为public方法定义了类对外提供的功能，阅读代码的时候，应该先关注public方法
 *
 *
 *
 * ********************************************************************
 * protected
 * protected作用于继承关系。定义为 protected 的字段和方法可以被子类访问，以及子类的子类：
 *
 * package abc; *
 * public class Hello { *
 *     protected void hi() {         }// protected方法:
 * }
 * 上面的protected方法可以被继承的类访问：
 *
 * package xyz; *
 * class Main extends Hello {
 *     void foo() {
 *         new Hello().hi();// 可以访问protected方法:
 *     }
 * }
 *
 *
 * ********************************************************************
 * default
 * 包作用域, 是指一个类允许访问同一个package的class，以及没有public、protected、private修饰的字段和方法。 *
 *
 * package abc; *
 * class Hello { *     // package权限的类:
 *     void hi() { *     }// package权限的方法:
 * }
 *
 * 只要在同一个包，就可以访问package权限的class、field和method：
 *
 * package abc; *
 * class Main {
 *     void foo() {
 *         Hello h = new Hello();// 可以访问package权限的类:
 *         h.hi();// 可以调用package权限的方法:
 *     }
 * }
 *
 *
 *
 * ********************************************************************
 * public
 * 定义为public的class、interface 可以被其他任何类访问：
 *
 * 1.
 * package abc; *
 * public class Hello { * }
 * 上面的Hello 是 public，因此，可以被其他包的类访问：
 *
 * package xyz; *
 * class Main {
 *     void foo() { *
 *         Hello h = new Hello();// Main可以访问Hello
 *     }
 * }
 *
 * 2.
 * 定义为public的field、method可以被其他类访问，前提是首先有访问class的权限：
 *
 * package abc; *
 * public class Hello {
 *     public void hi() { *     }
 * }
 *
 * 上面的hi()方法是public，可以被其他类调用，前提是首先要能访问Hello类： *
 * package xyz; *
 * class Main {
 *     void foo() {
 *         new Hello().hi();
 *     }
 * }
 *
 *
 * @author welldo
 * @date 2020/8/9
 */
class Scope12 {
}


class Scope13 {
}