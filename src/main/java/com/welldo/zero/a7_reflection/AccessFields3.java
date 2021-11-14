package com.welldo.zero.a7_reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 访问字段
 * !!! 通过反射读写字段是一种非常规方法，它会破坏对象的封装。
 *
 * 1.
 * 如何通过Class实例获取字段信息。Class类提供了以下几个方法来获取字段：
 *  Field getField(name)：根据字段名获取某个public的field（包括父类）
 *  Field getDeclaredField(name)：根据字段名获取当前类的某个field（不包括父类）
 *  Field[] getFields()：获取所有public的field（包括父类）
 *  Field[] getDeclaredFields()：获取当前类的所有field（不包括父类）
 *
 * 2.
 * 一个Field对象包含了一个字段的所有信息：
 *      getName()：返回字段名称，例如，"name"；
 *      getType()：返回字段类型，也是一个Class实例，例如，String.class；
 *      getModifiers()：返回字段的修饰符，它是一个int，不同的bit表示不同的含义。
 *
 * 3.利用反射拿到字段的一个Field实例只是第一步，我们还可以拿到一个实例对应的该字段的值。
 * 并且可以给字段赋值.
 *
 * 4.如果使用反射可以获取private字段的值，那么类的封装还有什么意义？
 * 答案是正常情况下，我们总是通过p.name来访问Person的name字段，编译器会根据public、protected和private决定是否允许访问字段，这样就达到了数据封装的目的。
 * 而反射是一种非常规的用法，使用反射，首先代码非常繁琐，
 * 其次，它更多地是给工具或者底层框架来使用，目的是在不知道目标实例任何信息的情况下(需要知道字段名)，获取特定字段的值。
 *
 * 5.setAccessible(true)可能会失败。
 * 如果JVM运行期存在SecurityManager，那么它会根据规则进行检查，有可能阻止setAccessible(true)
 *
 *
 * @author welldo
 * @date 2020/8/15
 */
public class AccessFields3 {
    public static void main(String[] args) throws Exception {
        //1.
        Class stdClass = Student.class;
        System.out.println(stdClass.getField("score"));// 获取public字段"score":
        System.out.println(stdClass.getField("name"));// 获取继承的public字段"name":
        System.out.println(stdClass.getDeclaredField("grade"));// 获取private字段"grade":

        //2.
        System.out.println("---------");
        Field f = String.class.getDeclaredField("value");
        f.getName(); // "value"
        f.getType();// class [c 表示char[]类型
        int m = f.getModifiers();//这里m = 18
        Modifier.isFinal(m); // true
        Modifier.isPublic(m); // false
        Modifier.isProtected(m); // false
        Modifier.isPrivate(m); // true
        Modifier.isStatic(m); // false

        //3.
        System.out.println("---------");
        Person p = new Person("Xiao Ming");
        Class c = p.getClass();
        Field nameField = c.getDeclaredField("name");

        //调用Field.setAccessible(true)的意思是，别管这个字段是不是public，一律允许访问。
        nameField.setAccessible(true);
        System.out.println(nameField.get(p));//获取名字

        //设置字段的值
        nameField.set(p,"welldo");
        System.out.println(p.getName());//获取名字

    }
}

class Student extends Person {
    public int score;
    private int grade;
}

class Person {
    public String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
}
