package com.welldo.zero.a7_reflection;

import com.welldo.zero.a4_oop.base.Plymorphic5;
import java.lang.reflect.Method;

/**
 * 访问方法
 *
 *  1.
 * 同样的，可以通过Class实例获取所有Method信息。Class类提供了以下几个方法来获取Method：
 *      Method getMethod(name, Class...)：获取某个public的Method（包括父类）
 *      Method getDeclaredMethod(name, Class...)：获取当前类的某个Method（不包括父类）
 *      Method[] getMethods()：获取所有public的Method（包括父类）
 *      Method[] getDeclaredMethods()：获取当前类的所有Method（不包括父类）
 *
 *
 * 2. * 一个Method对象包含一个方法的所有信息：
 *      getName()：返回方法名称，例如："getScore"；
 *      getReturnType()：返回方法返回值类型，也是一个Class实例，例如：String.class；
 *      getParameterTypes()：返回方法的参数类型，是一个Class数组，例如：{String.class, int.class}；
 *      getModifiers()：返回方法的修饰符，它是一个int，不同的bit表示不同的含义。
 *
 * 2.5 同样, 我们通过Method.setAccessible(true)允许其调用public 和 非public方法
 * 同样, setAccessible(true)可能会失败
 *
 * 3.调用方法
 *
 * 4.调用静态方法
 *
 * 5. 多态 {@link Plymorphic5}
 * 使用反射调用方法时，仍然遵循多态原则：即总是调用实际类型的覆写方法（如果存在）
 *
 * @author welldo
 * @date 2020/8/15
 */
public class AccessMethod4 {
    public static void main(String[] args) throws Exception {
        //1.
        Class stdClass = Student4.class;
        // 获取public方法getScore，参数为String:
        System.out.println(stdClass.getMethod("getScore", String.class));

        // 获取继承的public方法getName，无参数:
        System.out.println(stdClass.getMethod("getName"));

        // 获取private方法getGrade，参数为int:
        System.out.println(stdClass.getDeclaredMethod("getGrade", int.class));


        //3.调用方法
        System.out.println("3.-------------------");
        String s = "Hello world";
        String world = s.substring(6); // "world"

        // 获取String substring(int)方法，参数为int:
        Method m1 = String.class.getMethod("substring", int.class);
        m1.setAccessible(true);
        // 在s对象上,用该反射方法,获取结果:
        String r = (String) m1.invoke(s, 6);
        System.out.println("调用方法: "+r);

        // 获取String substring(int)重载方法，参数为int,int
        Method m2 = String.class.getMethod("substring", int.class,int.class);
        m2.setAccessible(true);
        String r2 = (String) m2.invoke(s, 0, 5);
        System.out.println("调用重载方法: "+r2);

        //4.调用静态方法
        // 如果获取到的Method表示一个静态方法，调用静态方法时，由于无需指定实例对象，
        // 所以invoke方法传入的第一个参数永远为null。我们以Integer.parseInt(String)为例：

        // 获取Integer.parseInt(String)方法，参数为String:
        Method m4 = Integer.class.getMethod("parseInt", String.class);
        m4.setAccessible(true);
        // 调用该静态方法并获取结果:
        Integer n = (Integer) m4.invoke(null, "12345");
        // 打印调用结果:
        System.out.println(n);

        System.out.println("---------------");

        //5.多态
        // 一个Person类定义了hello()方法，并且它的子类Student也覆写了hello()方法，
        // 那么，从Person.class获取的Method，作用于 Student 实例时，调用的方法到底是哪个？
        // 获取Person的hello方法:
        Method h = Person4.class.getMethod("hello");
        // 对Student实例调用hello方法:
        h.invoke(new Student4());
        h.invoke(new Person4());
    }

}

class Student4 extends Person4 {
    public int getScore(String type) {
        return 99;
    }
    private int getGrade(int year) {
        return 1;
    }

    @Override
    public void hello() {
        System.out.println("子类:hello");
    }
}

class Person4 {
    public String getName() {
        return "welldo";
    }

    public void hello() {
        System.out.println("父类:hello");
    }
}


