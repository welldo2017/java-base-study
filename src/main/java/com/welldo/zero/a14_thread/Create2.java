package com.welldo.zero.a14_thread;

import com.welldo.zero.a4_oop.base.Lambda15;

/**
 * 创建新线程
 *
 * 0.Java语言内置了多线程支持。
 * 当Java程序启动的时候，实际上是启动了一个JVM进程，然后，JVM启动主线程来执行main()方法。
 * 在main()方法中，我们又可以启动其他线程。
 *
 * 1.
 * 要创建一个新线程非常容易
 * 方法11：从Thread派生一个自定义类，然后覆写run()方法：再实例化, 再调用它的start()方法：
 * 方法12：创建Thread实例时，构造器中传入一个Runnable实例, 再调用它的start()方法：
 * 方法13：方法12可以简写成lambda 表达式，参考 {@link Lambda15}
 *
 *
 * 2.必须调用Thread实例的start()方法才能启动新线程, 并在新线程中执行run()方法。
 * 要特别注意：直接调用Thread实例的run()方法是无效的：
 *
 *
 * 3.线程的优先级
 * Thread.setPriority(int n) // 1~10, 默认值5
 * 操作系统对高优先级线程可能调度更频繁，(注意,是可能)
 * so我们决不能通过设置优先级来确保高优先级的线程一定会先执行。
 *
 * 4.分析
 *
 * @author welldo
 * @date 2020/9/2
 */
public class Create2 {

    public static void main(String[] args) {
        // test123();
        // analysis4();
        test2();
    }

    /**
     * 2.直接调用run()方法，相当于调用了一个普通的Java方法，当前线程并没有任何改变，也不会启动新线程。
     * 实际上是在main()方法内部又调用了run()方法，打印语句是在main线程中执行的，没有任何新线程被创建。
     */
    public static void test2() {
        Thread t2 = new MyThread1();
        t2.run();
    }

    public static void test123() {
        //11.
        Thread t11 = new MyThread1();
        t11.start(); // 启动新线程

        //12.
        Thread t12 = new Thread(new myRunnable1());
        t12.start();

        //13.lambda
        Runnable runnable = ()->{System.out.println("13. lambda");};
        Thread t13 = new Thread(runnable);
        t13.start();
    }


    /**
     * 4.分析
     * 看线程的执行顺序：
     * main线程肯定是先打印main start，再打印main end；
     * t线程肯定是先打印thread run，再打印thread end。
     *
     * 但是，除了可以肯定，main start会先打印外，main end打印在thread run之前、thread end之后或者之间，都无法确定。
     * 因为从t线程开始运行以后，两个线程就开始同时运行了，并且由操作系统调度，程序本身无法确定线程的调度顺序。
     *
     * 要模拟并发执行的效果，我们可以在线程中调用Thread.sleep()，强迫当前线程暂停一段时间：
     */
    public static void analysis4() {
        System.out.println("main start...");

        //todo 这是什么神仙语法？研究一下
        Thread t = new Thread() {
            public void run() {
                System.out.println("thread run...");
                System.out.println("thread end.");
            }
        };
        t.start();//调用start()启动新线程

        //让main线程睡一下， 就可以看到两个线程执行的先后顺序
        try {
            Thread.sleep(10);//todo 睡眠的是哪个线程?
        } catch (InterruptedException e) {

        }

        System.out.println("main end...");
    }
}

//11
class MyThread1 extends Thread {
    @Override
    public void run() {
        System.out.println("11. MyThread1");
    }
}
//12
class myRunnable1 implements Runnable{

    @Override
    public void run() {
        System.out.println("12. myRunnable1");
    }
}
