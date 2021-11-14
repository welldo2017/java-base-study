package com.welldo.zero.a14_thread;

/**
 * 中断线程
 *
 * 0.
 * 中断线程: AB两个线程, 就是a线程给b线程发一个信号，b线程收到信号后结束执行run()方法。
 * 举个栗子：用户可能在下载过程中点“取消”，这时，程序就需要中断下载线程.
 *
 *
 * 1. 中断一个线程非常简单，方法1
 * 只需要在其他线程中对目标线程调用interrupt()方法，目标线程需要反复检测自身状态是否是interrupted状态，如果是，就立刻结束运行。
 *       方法1的补充 见{@link Interrupt5}
 *
 * 3.中断一个线程非常简单，方法2
 * 另一个常用的方法是设置标志位。用一个 running 标志位来标识线程是否应该继续运行.
 *
 * 看代码:
 * 标志位  running 是一个线程间共享的变量。线程间共享变量需要使用 volatile 关键字标记
 * 为什么要对线程间共享的变量用关键字volatile声明？
 *
 * 先看结论:
 * volatile关键字解决的是可见性问题：当一个线程修改了某个共享变量的值，其他线程能够立刻看到修改后的值。
 *
 * 原因:
 * 在Java虚拟机中，变量的值保存在主内存中，但是，当线程访问变量时，它会先获取一个副本，并保存在自己的工作内存中。
 * 如果线程修改了变量的值，虚拟机会在某个时刻把修改后的值回写到主内存，但是，这个时间是不确定的！
 *
 * ┌ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ┐
 * |          Main Memory          |
 * |  ┌───────┐┌───────┐┌───────┐  |
 * │  │ var A ││ var B ││ var C │  │
 * |  └───────┘└───────┘└───────┘  |
 * │     │ ▲               │ ▲     │
 *  ─ ─ ─│─│─ ─ ─ ─ ─ ─ ─ ─│─│─ ─ ─
 *       │ │               │ │
 * ┌ ─ ─ ┼ ┼ ─ ─ ┐   ┌ ─ ─ ┼ ┼ ─ ─ ┐
 *       ▼ │               ▼ │
 * │  ┌───────┐  │   │  ┌───────┐  │
 *    │ var A │         │ var C │
 * │  └───────┘  │   │  └───────┘  │
 *    Thread 1          Thread 2
 * |   工作内存1   |   |  工作内存 2  |
 * └ ─ ─ ─ ─ ─ ─ ┘   └ ─ ─ ─ ─ ─ ─ ┘
 * 例如，主内存的变量a = true，线程1执行a = false时，它在此刻仅仅是把变量a的副本变成了false，主内存的变量a还是true，
 * 在JVM把修改后的a回写到主内存之前，其他线程读取到的a的值仍然是true，这就造成了多线程之间共享的变量不一致。
 *
 * 因此，volatile关键字的目的是告诉虚拟机：
 *      每次访问变量时，总是获取主内存的最新值；
 *      每次修改变量后，立刻回写到主内存。
 *
 *
 * @author welldo
 * @date 2020/9/3
 */
public class Interrupt4 {
    public static void main(String[] args) throws InterruptedException {
       // test1();
        test3();
    }

    /**
     * 3.在外部线程中，通过把HelloThread43.running置为false，就可以让线程结束
     */
    static void test3() throws InterruptedException {
        HelloThread43 hello = new HelloThread43();
        hello.start();
        Thread.sleep(5);//main线程暂停3ms
        hello.running = false;//更改标志位, 让hello线程停止
    }

    /**
     * 1.仔细看代码，main线程通过调用t.interrupt()方法中断t线程，
     * (但是要注意，interrupt()方法仅仅向t41线程发出了“中断请求”，至于t41线程是否能立刻响应，要看具体代码)
     * 而t41线程的while循环会检测isInterrupted()，
     * 所以上述代码能正确响应interrupt() 请求，使得自身立刻结束运行run()方法。
     */
    static void test1() throws InterruptedException {
        Thread t41 = new MyThread41();
        t41.start();
        Thread.sleep(3); // 暂停main线程n毫秒

        t41.interrupt(); // main线程下令中断t线程
        t41.join(); // main线程处于等待状态 (等待t线程结束后, 才往下执行)
        System.out.println("end");
    }

}


//1
class MyThread41 extends Thread {
    public void run() {
        int n = 0;
        while (! isInterrupted()) {
            n ++;//第1行
            //第2行, todo 疑问, main线程下令中断t线程 时, 线程会不会在 第2行 停止?
            System.out.println(n + " hello!");//第3行
        }
    }
    //todo "线程退出就是run()方法执行完毕" 如何理解
}


//3
class HelloThread43 extends Thread {
    public volatile boolean running = true;

    @Override
    public void run() {
        int n = 0;
        while (running) {
            n ++;
            System.out.println(n + " hello!");
        }
        System.out.println("end!");
    }
}