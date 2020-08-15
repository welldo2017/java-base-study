package com.welldo.zero.oop_4.base;

/**
 * 向上转型
 * 把一个子类类型安全地变为父类类型的赋值，被称为向上转型（upcasting）。
 *
 * @author welldo
 * @date 2020/8/8
 */
public class Cast4 {

    public static void main(String[] args) {

        //这是因为Student继承自Person，因此，它拥有Person的全部功能
        // 把一个子类类型安全地变为父类类型的赋值，被称为向上转型（upcasting）。
        Person4 person4 = new Student4();


        Person4 p1 = new Student4();
        Person4 p2 = new Person4();

        /*
        p1 实际指向Student实例，p2实际指向Person实例。
        在向下转型的时候，把p1转型为Student会成功，因为p1确实指向Student实例
        把p2转型为Student会失败，因为p2的实际类型是Person
        不能把父类变为子类，因为子类功能比父类多，多的功能无法凭空变出来

        为了避免向下转型出错，Java提供了instanceof操作符，可以先判断一个实例究竟是不是某种类型

         */
        Student4 s1 = (Student4) p1;//ok
        // Student4 s2 = (Student4) p2;//wrong ! ClassCastExceptiond

        if (p2 instanceof Student4) {
            System.out.println("ok");
        } else
            System.out.println("nok");


    }


}


class Person4 {
    protected String zhongZu;
}


class Student4 extends Person4 {
    private String name;
}




