package com.welldo.zero.a7_reflection;

import java.lang.reflect.Constructor;

/**
 * 调用构造方法
 *
 * 0.
 * 通过Class实例获取Constructor的方法如下：
 *      getConstructor(Class...)：获取某个public的Constructor；
 *      getDeclaredConstructor(Class...)：获取某个Constructor；
 *      getConstructors()：获取所有public的Constructor；
 *      getDeclaredConstructors()：获取所有Constructor。
 *
 * 注意Constructor总是当前类定义的构造方法，和父类无关，因此不存在多态的问题。
 *
 * 1.
 * 我们通常使用new操作符创建新的实例： * Person p = new Person();
 * 如果通过反射来创建新的实例，可以调用Class提供的newInstance()方法： * Person p = Person.class.newInstance();
 * 局限是，它只能调用该类的public无参数构造方法
 *
 * 2.
 * 为了调用任意的构造方法，Java的反射API提供了Constructor对象，它包含一个构造方法的所有信息，可以创建一个实例。Constructor对象和Method非常类似，不同之处仅在于它是一个构造方法，并且，调用结果总是返回实例：
 *
 * @author welldo
 * @date 2020/8/15
 */
public class AccessConstructor5 {
    public static void main(String[] args) throws Exception {
        // 获取构造方法Integer(int):
        Constructor cons1 = Integer.class.getConstructor(int.class);
        // 调用构造方法:
        Integer n1 = (Integer) cons1.newInstance(123);
        System.out.println(n1);

        // 获取构造方法Integer(String)
        Constructor cons2 = Integer.class.getConstructor(String.class);
        Integer n2 = (Integer) cons2.newInstance("456");
        System.out.println(n2);
    }
}
