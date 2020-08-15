package com.welldo.zero.reflection7;

import java.io.DataInputStream;
import java.lang.reflect.Constructor;

/**
 * 获取继承关系
 *
 * 1.有了Class实例，我们还可以使用 getSuperclass ,获取它的父类的Class：
 * !!!注意: 只能获取直接父类,不包括父类的父类
 *
 * 2. * 由于一个类可能实现一个或多个接口，通过 getInterfaces() 我们就可以查询到这个类实现的接口类型
 * !!!注意：只返回当前类直接实现的接口类型，并不包括其父类实现的接口类型：
 * 如果一个类没有实现任何interface，那么getInterfaces()返回空数组。
 *
 * 3.对接口调用 getSuperclass, 会得到null,仍然只能用 getInterfaces()
 *
 * @author welldo
 * @date 2020/8/15
 */
public class AccessExtends6 {
    public static void main(String[] args) throws Exception {
        //1.获取父类
        Class i = Integer.class;

        Class n = i.getSuperclass();//获取到父类 Number
        System.out.println(n);

        Class o = n.getSuperclass();//获取到父类 object
        System.out.println(o);

        System.out.println(o.getSuperclass());//object没有父类, 所以为null

        System.out.println("-------------------");
        //2.查询实现的接口
        Class[] is = i.getInterfaces();
        for (Class item : is) {
            System.out.println(item);
        }

        Class[] person6Interface = Person6.class.getInterfaces();
        if (person6Interface.length == 0) {
            System.out.println("当前类没有实现任何接口");
        }


        //3.对接口调用 getSuperclass, 会得到null
        System.out.println("-------------------");
        System.out.println(java.io.Closeable.class.getSuperclass()); // null


    }
}

class Person6 {

}
