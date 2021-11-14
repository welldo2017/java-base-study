package com.welldo.zero.a4_oop.core_class;

/**
 * StringBuilder
 *
 * 1. Java编译器对String做了特殊处理，使得我们可以直接用+拼接字符串
 *
 * 为了能高效拼接字符串，Java标准库提供了StringBuilder，它是一个可变对象，可以预分配缓冲区，
 * 这样，往StringBuilder中新增字符时，不会创建新的临时对象
 * StringBuilder sb = new StringBuilder(1024);
 * for (int i = 0; i < 1000; i++) {
 *     sb.append(',');
 *     sb.append(i);
 * }
 * String s = sb.toString();
 *
 *
 * 2. StringBuilder还可以进行链式操作：
 * 如果我们查看StringBuilder的源码，可以发现，进行链式操作的关键是，定义的append()方法会返回this，
 * 这样，就可以不断调用自身的其他方法
 *
 *
 * 3.仿照StringBuilder，我们也可以设计支持链式操作的类。例如，一个可以不断增加的计数器
 *
 *
 * @author welldo
 * @date 2020/8/10
 */
public class StringBuilder3 {

    public static void main(String[] args) {
        //2.
        StringBuilder sb = new StringBuilder(1024);
        sb.append("Mr ")
                .append("Bob")
                .append("!")
                .insert(0, "Hello, ");//Hello, Mr Bob!
        System.out.println(sb.toString());


        //3.链式操作
        Adder adder = new Adder();
        adder.add(3).add(5).inc().inc();
        System.out.println(adder.value());
    }
}


class Adder{
    private int sum = 0 ;

    public Adder add(int n){
        sum += n;
        return this;
    }

    public Adder inc(){
        sum ++;
        return  this;
    }

    public int value(){
        return this.sum;
    }

}























