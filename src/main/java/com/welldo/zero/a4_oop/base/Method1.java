package com.welldo.zero.a4_oop.base;

/**
 * 方法
 * 直接把field用public暴露给外部可能会破坏封装性
 *
 * @author welldo
 * @date 2020/8/8
 */
public class Method1 {
    public static void main(String[] args) {
        Person1 ming = new Person1();

        //1. public字段, 外部可访问
        ming.name = "Xiao Ming";//错误示范
        ming.age = -99; //错误示范//显然，直接操作field，容易乱赋值.


        //2. 为了避免外部代码直接去访问field，我们可以用private修饰field，拒绝外部访问：
        ming.setId(1);//正确示范


        //3. 无法调用private方法, 只能调用public方法
        ming.getAge();
        // ming.calcAge();
    }
}


class Person1 {
    //1. public字段, 外部可访问
    public String name; //错误示范
    public int age; //错误示范




    //2. 为了避免外部代码直接去访问field，我们可以用private修饰field，拒绝外部访问：
    //虽然外部代码不能直接修改private字段，但是，外部代码可以调用方法setName()和setAge()来间接修改private字段。
    // 在方法内部，我们就有机会检查参数对不对。
    // 比如，setAge()就会检查传入的参数，参数超出了范围，直接报错。
    // 这样，外部代码就没有任何机会把age设置成不合理的值。
    private int id;//正确示范

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


    //3.private方法
    // 有public方法，自然就有private方法。
    // 和private字段一样，private方法不允许外部调用，那我们定义private方法有什么用？
    // 定义private方法的理由是内部方法是可以调用private方法的。例如：
    private int birth;
    public int getAge() {
        return calcAge(); // 调用private方法
    }
    private int calcAge() {
        // 各种操作....
        return 555;
    }
}