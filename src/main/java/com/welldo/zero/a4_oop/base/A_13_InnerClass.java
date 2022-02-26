package com.welldo.zero.a4_oop.base;


import java.util.HashMap;

/**
 * 内部类
 * Java的内部类分为好几种，通常情况用得不多，但也需要了解它们是如何使用的。
 *
 * 1.inner class
 * 如果一个类定义在另一个类的内部，这个类就是Inner Class：(看代码)
 *
 * 2.匿名内部类
 * 在方法内部，通过匿名类（Anonymous Class）来定义(看代码)
 * 2.1 继承某接口
 * 2.2 继承普通类.
 *
 *
 */
public class A_13_InnerClass {

    public static void main(String[] args) {
        /*
         * 1.它与普通类有个最大的不同，就是Inner Class的实例不能单独存在，必须依附于一个Outer Class的实例。
         * 调用Outer实例的new 关键字, 来创建Inner实例
         */
        Outer out = new Outer("out");
        Outer.Inner inner = out.new Inner("inner");
        inner.hello();
        System.out.println();


        //2.1
        out.sayHello();
        System.out.println();

        //2.继承普通类.
        HashMap<Integer, Integer> map2 = new HashMap<Integer, Integer>() {}; // 匿名类,只不过这个类,啥也没干.
        //map2看起来是hashmap, 实际上是hashmap的子类, 向上转型成了 hashmap.
        // 真正的类名是  A_13_InnerClass$1
        System.out.println(map2.getClass());

        // 匿名类,并且用类的代码块,给自己赋值.
        HashMap<Integer, Integer> map3 = new HashMap<Integer, Integer>() {
            {
                put(1,100);
                put(2,200);
                this.put(3,300);//有一个this指向它自己
            }
        };
        System.out.println(map3.getClass());
        System.out.println(map3);
    }
}


class Outer {
    private String name;

    Outer(String name) {
        this.name = name;
    }

    class Inner {   // 1. 定义了一个Inner Class
        private String name;

        Inner(String name) {
            this.name = name;
        }

        void hello() {
            //Inner Class除了有一个this指向它自己，还隐含地持有一个Outer Class实例，可以用Outer.this访问这个实例
            System.out.println("内部类的名字: " + this.name);
            System.out.println("外部类的名字: " + Outer.this.name);
        }
    }


    /**
     * 2.1 定义匿名 内部类,继承某接口
     * 我们在方法内部实例化了一个 Runnable。  * Runnable本身是接口，接口是不能实例化的，
     * 所以这里,实际上是定义了一个实现了 Runnable 接口的匿名类，并且通过new 关键字实例化该匿名类，然后转型为Runnable。
     *
     * 定义匿名类的写法如下：
     * Runnable r = new Runnable() {
     *     // 实现必要的抽象方法...
     * };
     *
     * 之所以我们要定义匿名类，是因为在这里,我们通常不关心类名,那它的类名到底是什么呢?
     * 查看字节码文件,
     * Outer类被编译为Outer.class，而匿名类被编译为Outer$1.class。
     * 如果有多个匿名类，Java编译器会将每个匿名类依次命名为Outer$1、Outer$2、Outer$3……
     */
    void sayHello(){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello, " + Outer.this.name);
            }
        };
        System.out.println(r.getClass());//查看真正的类名, Outer$1

        //Outer$2
        System.out.println(new Runnable() {
            @Override
            public void run() {

            }
        }.getClass());

        //Outer$3
        System.out.println(new Runnable() {
            @Override
            public void run() {

            }
        }.getClass());
    }


}