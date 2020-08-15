package com.welldo.zero.reflection7;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 * https://www.jianshu.com/p/95970b089360
 *
 * 1.Java的class和interface的区别：
 *      可以实例化class（非abstract）；
 *      不能实例化interface。
 *
 * 所以, 接口的变量,经常指向一个实现类的实例的引用
 * List<Object> objects = new ArrayList<>();//List是接口,arrayList是实现类
 * 那么, 有没有可能不编写实现类，直接在运行期创建某个interface的实例呢？
 * Java标准库提供了一种动态代理（Dynamic Proxy）的机制：可以在运行期动态创建某个interface的实例。
 *
 * 2.
 * 动态代理，是和静态相对应的。我们来看静态代码怎么写：
 *
 * 3.
 * 动态代码，我们仍然先定义了接口Hello，但是我们并不去编写实现类，
 * 而是直接通过JDK提供的一个 Proxy.newProxyInstance() 创建一个Hello接口对象。
 * 这种没有实现类但是在运行期动态创建了一个接口对象的方式，我们称为动态代码。
 * JDK提供的动态创建接口对象的方式，就叫动态代理。
 *
 * 一个最简单的动态代理实现如下：
 * 在运行期动态创建一个interface实例的方法如下：
 * a. 定义一个InvocationHandler实例，它负责实现接口的方法调用；
 * b. 通过Proxy.newProxyInstance()创建interface实例，它需要3个参数：
 *      使用的ClassLoader，通常就是接口类的ClassLoader；
 *      需要实现的接口数组，至少需要传入一个接口进去；
 *      用来处理接口方法调用的InvocationHandler实例。
 * c.将返回的Object强制转型为接口。
 *
 *
 * @author welldo
 * @date 2020/8/15
 */
public class DynamicProxy8 {
    public static void main(String[] args) {

        //2.创建实例，转型为接口并调用：
        Hello helloForStatic = new HelloWorld();
        helloForStatic.morning("Bob");


        //3.一个最简单的动态代理实现如下：
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method);
                if (method.getName().equals("morning")) {
                    System.out.println("Good morning, " + args[0]);
                }
                return null;
            }
        };

        //生成实例, 并转成接口的类型
        Hello hello = (Hello) Proxy.newProxyInstance(
                Hello.class.getClassLoader(), // 传入ClassLoader
                new Class[] { Hello.class }, // 传入要实现的接口
                handler); // 传入处理调用方法的InvocationHandler
        hello.morning("3Bob");
    }
}


//2. 静态代理
interface Hello {
    void morning(String name);
}

class HelloWorld implements Hello {
    public void morning(String name) {
        System.out.println("Good morning, " + name);
    }
}