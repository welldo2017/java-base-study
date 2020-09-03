package com.welldo.zero.thread14;

/**
 * 线程的状态
 *
 * 0.
 * 在Java程序中，一个线程对象只能调用一次start()方法启动新线程，并在新线程中执行run()方法。一旦run()方法执行完毕，线程就结束了。
 *
 * 1.
 * 因此，Java线程的状态有以下3种：
 *      1. New：新创建的线程，尚未执行；
 *      2.运行中
 *          Runnable：正常运行中;
 *          Blocked：运行中的线程，因为某些操作被阻塞而挂起；
 *          Waiting：运行中的线程，因为某些操作在等待中；
 *          Timed Waiting：运行中的线程，因为执行sleep()方法正在计时等待；
 *      3.Terminated：线程已终止，因为run()方法执行完毕。
 *
 * 用一个状态转移图表示如下：
 *                          ┌─────────────┐
 *                          │     New     │新创建
 *                          └─────────────┘
 *                                 │
 *                                 ▼
 * ┌ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─  ─  ─  ─  ─  ─  ─  ─  ─  ─  ─  ─ ┐
 * |┌─────────────┐ ┌─────────────┐┌─────────────┐ ┌─────────────┐|
 * ││  Runnable   │ │   Blocked   ││   Waiting   │ │Timed Waiting││ 运行中
 * |└─────────────┘ └─────────────┘└─────────────┘ └─────────────┘|
 *  ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─
 *                                 │
 *                                 ▼
 *                          ┌─────────────┐
 *                          │ Terminated  │终止
 *                          └─────────────┘
 * https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/lang/Thread.State.html
 *
 * 2.
 * 当线程启动后，它可以在Runnable、Blocked、Waiting和Timed Waiting这几个状态之间切换，直到最后变成Terminated状态，线程终止。
 * 线程终止的原因有：
 *      线程正常终止：run()方法执行到return语句返回；
 *      线程意外终止：run()方法因为未捕获的异常导致线程终止；
 *      对某个线程的Thread实例调用stop()方法强制终止（强烈不推荐使用）。
 *
 * 3.join
 * 一个线程还可以等待另一个线程直到其运行结束。例如，main线程在启动t线程后，可以通过t.join()等待t线程结束后再继续运行：
 *
 * @author welldo
 * @date 2020/9/2
 */
public class Status3 {
    public static void main(String[] args) throws InterruptedException {

        /**
         * 3.当main线程对线程对象t调用join()方法时，主线程将等待 t线程运行结束，即join就是指等待该线程结束，然后才继续往下执行自身线程。
         * 所以，上述代码打印顺序可以肯定是main线程先打印start，t线程再打印hello，main线程最后再打印end。
         */
        Thread t = new Thread(() -> {
            System.out.println("t hello");
        });

        System.out.println("main start");
        t.start();
        t.join();
        System.out.println("mian end");
    }
}
