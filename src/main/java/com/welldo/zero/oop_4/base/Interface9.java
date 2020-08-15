package com.welldo.zero.oop_4.base;

/**
 * 接口
 *
 * 如果一个抽象类没有字段，所有方法全部都是抽象方法：
 * abstract class Person {
 *     public abstract void run();
 *     public abstract String getName();
 * }
 *
 *
 * default方法(jdk >= 1.8)
 * 在接口中，可以定义default方法。 * 实现类可以不必覆写default方法。
 * default方法的目的是，
 * 当我们需要给接口新增一个方法时，会涉及到修改全部子类
 * 如果新增的是default方法,那么子类就直接获得此方法, 按需修改.
 *
 *
 *
 * @author welldo
 * @date 2020/8/8
 */
public class Interface9 {
    public static void main(String[] args) {

        //直接获得default方法
        Student9 student9 = new Student9("ww");
        student9.sayHi();

        // 按需修改default方法
        Student9FixDefault sss = new Student9FixDefault("sss");
        sss.sayHi();
    }
}




//所谓interface，它连字段都不能有。
// 因为接口定义的所有方法默认都是public abstract的，所以这两个修饰符不需要写出来（写不写效果都一样）
interface Person9 {
    void run();
    String getName();



    default void sayHi() {
        System.out.println("default say hi");
    }
}


//一个interface可以继承自另一个interface。使用extends，它相当于扩展了接口的方法。例如：
interface littlePerson9 extends Person9{
    //此接口实际上有3个抽象方法签名.
    void jump();
}


class Student9 implements Person9 {
    private String name;

    public Student9(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(this.name + " run");
    }

    @Override
    public String getName() {
        return this.name;
    }
}


class Student9FixDefault implements Person9 {
    private String name;

    public Student9FixDefault(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(this.name + " run");
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void sayHi() {
        System.out.println("fix default say hi");
    }
}