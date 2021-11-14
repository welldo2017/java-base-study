package com.welldo.zero.a5_exception;

/**
 * 抛出异常
 *
 *
 * 1.抛出异常分两步：
 *      1.1 创建某个Exception的实例；
 *      1.2 用throw语句抛出。
 *
 *
 * 2. 捕获了某个异常后，又在catch子句中抛出新的异常，就相当于把抛出的异常类型“转换”了：
 *
 * 3. 为了能追踪到完整的异常栈，在构造异常的时候，把原始的Exception实例传进去，新的Exception就可以持有原始Exception信息
 *捕获到异常并再次抛出时，一定要留住原始异常，否则很难定位第一案发现场！
 *
 * 4.在try或者catch语句块中 有a操作, 并抛出异常(b操作)，finally语句是否会执行？
 * 会, 顺序为 a, finally, b
 *
 * 5.如果在执行finally语句时抛出异常，那么，catch语句的异常还能否继续抛出？
 *  finally抛出异常后，原来在catch中准备抛出的异常就“消失”了，因为只能抛出一个异常。
 *  没有被抛出的异常称为“被屏蔽”的异常（Suppressed Exception）。
 *
 *  绝大多数情况下，在finally中不要抛出异常。因此，我们通常不需要关心Suppressed Exception。
 *
 *
 * @author welldo
 * @date 2020/8/12
 */
public class Throw2 {

    public static void main(String[] args) {
        //2.捕获转型后的异常,打印出的异常栈类似： java.lang.IllegalArgumentException
        //这说明新的异常丢失了原始异常信息，我们已经看不到原始异常NullPointerException的信息了。
        // try {
        //     process1(null);
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }

        //3.
        try {
            process3(null);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    //2.
    static void process1(String s) {
        try {
            process2(s);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException();
        }
    }

    //1. 抛出异常
    static void process2(String s) {
        if (s==null) {
            throw new NullPointerException();
        }
    }


    //3.
    /*
    java.lang.IllegalArgumentException: java.lang.NullPointerException  //表面上, 异常在73行
	at com.welldo.zero.exception5.Throw2.process3(Throw2.java:73)
	at com.welldo.zero.exception5.Throw2.main(Throw2.java:33)

Caused by: java.lang.NullPointerException   //根源在于54 行的NullPointerException
	at com.welldo.zero.exception5.Throw2.process2(Throw2.java:54)
	at com.welldo.zero.exception5.Throw2.process3(Throw2.java:71)
     */
    static void process3(String s) {
        try {
            process2(s);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(e);//3.捕获到异常并再次抛出时，一定要留住原始异常，否则很难定位第一案发现场！
        }
    }


}
