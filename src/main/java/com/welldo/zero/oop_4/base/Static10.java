package com.welldo.zero.oop_4.base;

/**
 * 静态字段和静态方法
 *
 * 在一个 class 中定义的字段，我们称之为实例字段。实例字段的特点是，各个实例的同名字段互不影响。
 * 还有一种字段，是用static修饰的字段，称为静态字段：static field。
 *
 * 实例字段在每个实例中都有自己的一个独立“空间”，但是静态字段只有一个共享“空间”，所有实例都会共享该字段。
 *
 * 对于静态字段，无论修改哪个实例的静态字段，效果都是一样的：所有实例的静态字段都被修改了，原因是静态字段并不属于实例：
 *
 *         ┌──────────────────┐
 * ming ──>│Person instance   │
 *         ├──────────────────┤
 *         │name = "Xiao Ming"│
 *         │age = 12          │
 *         │number ───────────┼──┐    ┌─────────────┐
 *         └──────────────────┘  │    │Person class │
 *                               │    ├─────────────┤
 *                               ├───>│number = 99  │
 *         ┌──────────────────┐  │    └─────────────┘
 * hong ──>│Person instance   │  │
 *         ├──────────────────┤  │
 *         │name = "Xiao Hong"│  │
 *         │age = 15          │  │
 *         │number ───────────┼──┘
 *         └──────────────────┘
 *
 * 因此，不推荐用 "实例变量.静态字段" 去访问静态字段
 *
 *
 * 2. 静态方法
 * 静态方法经常用于工具类。例如： * Arrays.sort() * Math.random()
 *
 *
 *
 *
 * 3. 接口的静态字段
 * interface不能定义实例字段。但是，interface是可以有静态字段的，并且静态字段必须为final类型：
 * public interface Person {
 *     public static final int MALE = 1;
 *     public static final int FEMALE = 2;
 * }
 *
 * 实际上，因为interface的字段只能是public static final类型，所以我们可以把这些修饰符都去掉，上述代码可以简写为：
 * public interface Person {
 *     int MALE = 1;
 *     int FEMALE = 2;
 * }
 *
 *
 * @author welldo
 * @date 2020/8/8
 */
public class Static10 {
    public static void main(String[] args) {
        Person10 ming = new Person10("Xiao Ming", 12);
        Person10 hong = new Person10("Xiao Hong", 15);

        //小明修改公共字段,影响小红
        ming.number = 88;
        System.out.println(hong.number);

        //小hong修改公共字段,影响小ming
        hong.number = 99;
        System.out.println(ming.number);


        //2. 静态方法
        // 通过实例变量也可以调用静态方法，但这只是编译器自动帮我们把实例改写成类名而已。
        //查看反编译后的class文件可以证实.
        ming.setNumber(222);
        System.out.println(hong.number);
    }
}


class Person10 {
    public String name;
    public int age;

    public static int number;

    public Person10(String name, int age) {
        this.name = name;
        this.age = age;
    }


    //2. 静态方法
    //因为静态方法属于class而不属于实例，因此，静态方法内部，无法访问this变量，也无法访问实例字段，它只能访问静态字段。
    public static void setNumber(int value) {
        // this.age = value;//错误示范
        number = value;
    }
}

