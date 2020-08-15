package com.welldo.zero.oop_4.base;

/**
 * 继承
 * 继承是面向对象编程中非常强大的一种机制，它首先可以复用代码。
 * 当我们让Student从Person继承时，Student就获得了Person的所有功能，我们只需要为Student编写新增的功能。
 *
 *
 * 1. 在OOP的术语中，
 * 我们把Person称为超类（super class），父类（parent class），基类（base class），
 * 把Student称为子类（subclass），扩展类（extended class）
 *
 *
 * 2. 继承树
 * (java是单继承)
 * 在Java中，没有明确写extends的类，编译器会自动加上extends Object。
 * 所以，任何类，除了Object，都会继承自某个类。下图是Person、Student的继承树：
 * ┌───────────┐
 * │  Object   │
 * └───────────┘
 *       ▲
 *       │
 * ┌───────────┐
 * │  Person   │
 * └───────────┘
 *       ▲
 *       │
 * ┌───────────┐
 * │  Student3  │
 * └───────────┘
 *
 *
 * 3. protected
 * 继承有个特点，就是子类无法访问父类的private字段或者private方法。
 * 例如，Student类就无法访问Person类的name和age字段：
 *
 * 为了让子类可以访问父类的字段，我们需要把private改为protected。用protected修饰的字段可以被子类访问：
 * protected关键字可以把字段和方法的访问权限控制在继承树内部，一个protected字段和方法可以被其子类，孙子类所访问
 *
 *
 * 4. super
 * super关键字表示父类（超类）
 * 子类引用父类的字段时，可以用super.fieldName。
 * 实际上，这里使用super.name，或者this.name，或者name，效果都是一样的。编译器会自动定位到父类的name字段。
 * 但是，在某些时候，就必须使用super。
 *
 *
 * 5. todo 既然无法访问,那存在的意义是什么呢?
 *
 * @author welldo
 * @date 2020/8/8
 */
public class Extends3 {
    public static void main(String[] args) {
        //先访问
        Student3 student3 = new Student3();
        System.out.println(student3.zhongZu);

        //再修改, 修改的是自己的, 和父类无关
        student3.zhongZu = "american";
        System.out.println(student3.zhongZu);
        System.out.println(new Person3().zhongZu);

    }
}



class Person3 {
    private String name;// 不能被子类访问
    private int age;// 不能被子类访问

    protected String zhongZu = "hanZu";//种族, 可以被子类访问


    public Person3() {
    }

    public Person3(String name, int age) {
        this.name = name;
        this.age = age;
    }

}

//子类自动获得了父类的所有字段
class Student3 extends Person3 {
    // 不要重复name和age字段/方法,
    // 只需要定义新增score字段/方法:
    private int score;


    //3. protected
    public void hello() {
        // System.out.println(name);// 编译错误：无法访问name字段

        System.out.println(super.zhongZu);// 编译成功//4. 编译器会自动定位到父类的 zhongZu 字段。
        System.out.println(zhongZu);// 编译成功//4.编译器会自动定位到父类的 zhongZu 字段。
    }


    public Student3() {
    }

    //4. 在Java中，任何class的构造方法，第一行语句必须是调用父类的构造方法
    // 如果没有明确地调用父类的构造方法，编译器会帮我们自动加一句super();
    public Student3(String name, int age, int score) {
        // super();
        super(name,age);//这里必须显式 调用super
        this.score = score;
    }

}