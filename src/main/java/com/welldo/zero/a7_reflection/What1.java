package com.welldo.zero.a7_reflection;

/**
 * 什么是反射？
 * 反射就是 Reflection，Java的反射, 是指程序在运行期可以拿到一个对象的所有信息。
 *
 * 1.
 * 正常情况下，如果我们要调用一个对象的方法，或者访问一个对象的字段，通常会传入对象实例：
 * import com.itranswarp.learnjava.Person;
 * public class Main {
 *     String getFullName(Person p) {
 *         return p.getFirstName() + " " + p.getLastName();
 *     }
 * }
 *
 * 2. 但是，如果不能获得Person类，只有一个Object实例，比如这样：
 * String getFullName(Object obj) {
 *     return ???
 * }
 *
 * 3. 怎么办？有童鞋会说：强制转型啊！
 * String getFullName(Object obj) {
 *     Person p = (Person) obj;
 *     return p.getFirstName() + " " + p.getLastName();
 * }
 * 强制转型的时候，你会发现一个问题：编译上面的代码，仍然需要引用Person类。不然，去掉import语句，你看能不能编译通过？
 * 所以，反射是为了解决在运行期，对某个实例一无所知的情况下，如何调用其方法。
 *
 * @author welldo
 * @date 2020/8/14
 */
public class What1 {
}
