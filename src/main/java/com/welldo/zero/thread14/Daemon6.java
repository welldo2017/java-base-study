package com.welldo.zero.thread14;

import java.time.LocalDateTime;

/**
 * 守护线程 daemon
 *
 * 0.
 * 守护线程是指为其他线程服务的线程。
 *
 * !!! 注意, 某些概念可能和字面意思恰恰相反.
 * 守护线程, 是可有可无的进程, 当正常线程执行结束时, JVM为了退出, 就要杀死守护进程,
 *
 *
 * 1.一个Java程序实际上是一个JVM进程，JVM进程用一个主线程来执行main()方法，在主线程中，我们又可以启动多个线程。
 * 当所有线程都运行结束时，JVM退出，进程结束。
 * 如果有一个线程没有退出，JVM进程就不会退出。
 *
 * 2.
 * 但是有一种线程的目的就是无限循环, 比如定时任务(线程a). * 一分钟执行一次, or 一天执行一次, 无穷无尽, 执行到天荒地老
 * 也就是说, 只要这个线程(a)存在, JVM进程就无法结束。
 *
 * 但是，当其他线程(b,c)结束时，JVM进程又必须要结束，怎么办？
 * 答案, 是把a 置为守护线程（Daemon Thread）。守护线程(服务员)是指为其他线程(客人)服务的线程。
 * 客人都走了, 服务员可以不存在.
 *
 * 在JVM中，所有非守护线程都执行完毕后，虚拟机都会自动退出, 不用管守护线程.
 *
 * 3.
 * 如何创建守护线程呢？方法和普通线程一样，只是在调用start()方法前，调用setDaemon(true)把该线程标记为守护线程：
 *  Thread t = new MyThread();
 *  t.setDaemon(true);
 *  t.start();
 *
 *
 * 4.在守护线程中，编写代码要注意：
 * 守护线程不能持有任何需要关闭的资源，例如打开文件等，因为虚拟机退出时，守护线程没有任何机会来关闭文件，这会导致数据丢失。
 *
 * @author welldo
 * @date 2020/9/3
 */
public class Daemon6 {
    public static void main(String[] args) {
        Timer timer = new Timer();
        //如果不加这句, 5s后, main线程结束, timer线程仍然打印时间.
        // timer.setDaemon(true);
        timer.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {

        }
        System.out.println("main end");
    }

}

class Timer extends Thread{
    @Override
    public void run() {
        while (true){
            System.out.println(LocalDateTime.now());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}