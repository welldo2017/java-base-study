package com.welldo.zero.a4_oop.base;

/**
 * java8新特性: lambda表达式
 * https://www.zhihu.com/question/20125256/answer/324121308
 *
 * 0. 前置知识：接口 {@link Interface9}
 *
 * 1.什么是Lambda?
 * 对于一个Java变量，我们可以赋给其一个“值”。 String hello(变量) = "helloWorld"(值)
 *
 * 如果你想把“一块代码”赋给一个Java变量，应该怎么做呢？
 * 变量 aBlockOfCode
 * 代码 public void doSomeShit(String s){
 *         System.out.println(s);
 *     }
 *
 * 在Java 8之前，这个是做不到的。但是Java 8问世之后，利用Lambda特性，就可以做到了。
 * aBlockOfCode = public void doSomeShit(String s){
 *         System.out.println(s);
 *     }
 *
 * 当然，这个并不是一个很简洁的写法。所以, 我们可以移除一些没用的声明。
 *      public 等修饰符，多余;
 *      方法名，多余，因为方法整体已经赋值变量, 不需要用方法名来指代;
 *      void 等返回值类型，多余，编译器可以自己判断;
 *      String 等参数类型，多余，编译器可以自己判断;
 *      大括号，只有一行代码的时候可以不要大括号；
 *      参数和方法体 之间加上一个箭头符号“->”;
 *
 * 这样，我们就非常优雅的把“一块代码”赋给了一个变量。而“这块代码”，或者说“这个被赋给一个变量的方法”，就是一个Lambda表达式。
 * aBlockOfCode = (s) -> System.out.println(s);
 *
 * 2.
 * 变量aBlockOfCode的类型应该是什么？
 * 假设这里有个接口 MyLambdaInterface，里面只有一个需要实现的方法，而Lambda表达式本身，也就是”那段代码“，需要是这个接口的方法实现。
 * 简而言之就是，Lambda表达式本身就是一个接口的实现。(看代码)
 *
 * interface MyLambdaInterface{
 *     void doSomeShit(String s);
 * }
 * 接口的名字， 就是变量aBlockOfCode的类型；
 *
 * 这样，我们就得到了一个完整的Lambda表达式声明：
 * MyLambdaInterface aBlockOfCode = (s) -> System.out.println(s);
 *
 *
 * 3.有什么用？
 *  a. 最直观的作用就是使得代码变得异常简洁。
 *  b. 由于Lambda可以直接赋值给一个变量，我们就可以直接把Lambda作为参数传给方法
 *  c. 有些情况下，这个接口实现只需要用到一次。传统的Java 7必须要求你定义一个“污染环境”的接口实现MyInterfaceImpl，而相较之下Java 8的Lambda, 就显得干净很多。
 *
 *
 * @author welldo
 * @date 2020/9/2
 */
public class Lambda15 {
    public static void main(String[] args) {
        String hello = "helloWorld";

        // 3.a. 在没有lambda时， 需要这样写
        myLambda lambda3a = new myLambda();
        lambda3a.doSomeShit("jack");

        //3.a lambda写法
        MyLambdaInterface lambda3b = (s) -> System.out.println(s + "! this is a shit mountain");
        lambda3b.doSomeShit("rose");

    }
}

/**
 * 2. 这种只有一个接口函数需要被实现的接口类型，我们叫它”函数式接口“。
 * 为了避免后来的人在这个接口中增加接口函数导致其有多个接口函数需要被实现，变成"非函数接口”，
 * 我们可以在这个上面加上一个声明@FunctionalInterface, 这样别人就无法在里面添加新的接口函数了：
 *
 */
@FunctionalInterface
interface MyLambdaInterface{
    void doSomeShit(String s);
}


//3.
class myLambda implements MyLambdaInterface{
    @Override
    public void doSomeShit(String s) {
        System.out.println(s + "! this is a shit mountain");
    }
}


