package com.welldo.zero.a14_thread;

/**
 * 线程同步
 *
 * 1. 原子操作
 * 当多个线程同时运行，线程的调度由操作系统决定，程序本身无法决定。
 * 因此，任何一个线程都有可能在任何指令处被操作系统暂停，然后在某个时间段后继续执行。
 * 这个时候，有个单线程模型下不存在的问题就来了：如果多个线程同时读写共享变量，会出现数据不一致的问题。
 * 看代码
 *
 * 2.
 * 因为对变量进行读取和写入时，结果要正确，必须保证是原子操作。原子操作是指不能被中断的一个或一系列操作。
 *  对于语句：
 *  n = n + 1; 看上去是一行语句，实际上对应了3条指令：
 *  ILOAD
 *  IADD
 *  ISTORE
 *
 * 我们假设n的值是100，如果两个线程同时执行n = n + 1，得到的结果很可能不是102，而是101，原因在于：
 * time1时执行了线程1, 然后time2暂停线程1并执行线程2, 线程2完整执行后,再执行线程1
 * ┌───────┐    ┌───────┐
 * │Thread1│    │Thread2│
 * └───┬───┘    └───┬───┘
 *     │            │
 *     │ILOAD (100) │               //time1
 *     │            │ILOAD (100)    //time2
 *     │            │IADD           //time3
 *     │            │ISTORE (101)   //time4
 *     │IADD        │               //time5
 *     │ISTORE (101)│               //time6
 *     ▼            ▼
 *
 * 这说明多线程模型下，要保证逻辑正确，对共享变量进行读写时，必须保证一组指令以原子方式执行：即某一个线程执行时，其他线程必须等待：
 * ┌───────┐     ┌───────┐
 * │Thread1│     │Thread2│
 * └───┬───┘     └───┬───┘
 *     │-- lock --   │
 *     │ILOAD (100)  │
 *     │IADD         │
 *     │ISTORE (101) │
 *     │-- unlock -- │
 *     │             │-- lock --
 *     │             │ILOAD (101)
 *     │             │IADD
 *     │             │ISTORE (102)
 *     │             │-- unlock --
 *     ▼             ▼
 * 通过加锁和解锁的操作，就能保证3条指令总是在一个线程执行期间，不会有其他线程会进入此指令区间。
 * 即使在执行期线程被操作系统中断执行，其他线程也会因为无法获得锁导致无法进入此指令区间。
 * 这种加锁和解锁之间的代码块我们称之为临界区（Critical Section）
 *
 * 保证一段代码的原子性就是通过加锁和解锁实现的。Java程序使用 synchronized 关键字对一个对象进行加锁：
 * synchronized 保证了一对 { } 内的代码块在任意时刻最多只有一个线程能执行
 * synchronized(lock) {  n = n + 1; }
 *
 * 3. 对1的代码进行改写, 看代码
 * 此方法解决了多线程同步访问共享变量的正确性问题。但是它带来了性能下降(也就是慢)。
 * 因为synchronized代码块无法并发执行。此外，加锁和解锁需要消耗一定的时间.
 *
 *
 * 4.不需要synchronized的操作
 * JVM规范定义了几种原子操作：
 * 基本类型赋值，例如：int n = m；（!!! long和double除外）
 * 引用类型赋值，例如：List list = anotherList。
 *
 * @author welldo
 * @date 2020/9/7
 */
public class Synchronized7 {
    public static void main(String[] args) throws InterruptedException {
        test3();
    }

    /**
     * 3. 永远不会出现错误
     */
    static void test3() throws InterruptedException {
        AddThread73 add = new AddThread73();
        DecThread73 dec = new DecThread73();
        add.start();
        dec.start();
        add.join();
        dec.join();
        System.out.println(Counter73.count);
    }

    /**
     * 1. add线程累加1w, dec线程累减1w, 理论上count=0, 但是每次运行，结果可能都不一样. 原因看2
     */
    static void test1() throws InterruptedException {
        AddThread71 add = new AddThread71();
        DecThread71 dec = new DecThread71();
        add.start();
        dec.start();
        add.join();
        dec.join();
        System.out.println(Counter71.count);
    }
}


class Counter71 {
    public static int count = 0;
}

class AddThread71 extends Thread {
    public void run() {
        for (int i=0; i<10000; i++) { Counter71.count += 1; }
    }
}

class DecThread71 extends Thread {
    public void run() {
        for (int i=0; i<10000; i++) { Counter71.count -= 1; }
    }
}




class Counter73 {
    /**
     * 选择一个共享实例作为锁；
     *
     * 把需要原子执行的代码块,想象成一个集装箱,
     * 把锁想象成一扇门.
     * 只有一扇门(一把锁), 就只能同时供一条线程进出.
     * 如果多扇门,就可以供多条线程进出,就会出现同时修改,出现错误.
     *
     * 所以:我们只应该使用同一把锁
     * (JVM 可以保证同一个锁在任意时刻只能被一个线程获取)
     *
     * 使用synchronized的时候，锁住的是哪个对象非常重要。
     */
    public static final Object lock = new Object();
    public static int count = 0;
}

class AddThread73 extends Thread {
    /**
     * 3. 线程用Counter.lock实例作为锁，
     * 无论哪个线程, 在执行synchronized(Counter.lock) { ...  }代码块时，
     * 必须先获得锁，才能进入代码块进行。执行结束后，在synchronized语句块结束会自动释放锁。
     * 这样一来，对Counter.count变量进行读写就不可能同时进行。所以不会出现异常
     */
    public void run() {
        for (int i = 0; i < 10000; i++) {
            synchronized (Counter73.lock) {// 获取锁
                Counter73.count += 1;
            }// 释放锁
        }
    }
}

class DecThread73 extends Thread {
    public void run() {
        for (int i = 0; i < 9999; i++) {
            synchronized (Counter73.lock) {
                Counter73.count -= 1;
            }
        }
    }
}
