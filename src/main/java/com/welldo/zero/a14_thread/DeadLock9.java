package com.welldo.zero.a14_thread;

/**
 * 死锁
 *
 *
 * 1.
 * Java的synchronized锁是可重入锁；
 * 什么是可重入锁, 看代码
 *
 * 2.死锁
 * 一个线程可以获取一个锁后，再继续获取另一个锁。
 * 假如add()方法需要a锁与b锁; * 假如dec()方法需要b锁与a锁;
 *
 * 线程1和线程2如果分别执行add()和dec()方法时：
 *      线程1：进入add()，获得locka；
 *      线程2：进入dec()，获得lockb。
 * 随后：
 *      线程1：准备获得lockb，失败，等待中；
 *      线程2：准备获得locka，失败，等待中。
 *
 * 此时，两个线程各自持有不同的锁，然后各自试图获取对方手里的锁，造成了双方无限等待下去，这就是死锁。
 * 死锁发生后，没有任何机制能解除死锁，只能强制结束JVM进程。
 * 因此，在编写多线程应用时，要特别注意防止死锁。
 *
 * 3.
 * 那么我们应该如何避免死锁呢？
 * 答案是：线程获取锁的顺序要一致。即严格按照先获取lockA，再获取lockB的顺序，改写dec()方法如下：
 *
 * @author welldo
 * @date 2020/9/7
 */
public class DeadLock9 {

    private static  final Object lockA = new Object();
    private static  final Object lockB = new Object();

    private int value = 0;
    private int another = 0;


    //2.
    public void add(int m) {
        synchronized(lockA) { // 获得lockA的锁
            this.value += m;
            synchronized(lockB) { // 获得lockB的锁
                this.another += m;
            } // 释放lockB的锁
        } // 释放lockA的锁
    }

    //2.
    public void dec(int m) {
        synchronized(lockB) { // 获得lockB的锁
            this.another -= m;
            synchronized(lockA) { // 获得lockA的锁
                this.value -= m;
            } // 释放lockA的锁
        } // 释放lockB的锁
    }

    //3.
    public void dec3(int m) {
        synchronized(lockA) { // 获得lockA的锁
            this.value -= m;
            synchronized(lockB) { // 获得lockB的锁
                this.another -= m;
            } // 释放lockB的锁
        } // 释放lockA的锁
    }


}



class Counter9 {
    private int count = 0;


    /**
     * 1. 一旦线程执行到synchronized修饰的add()方法内部，说明它已经获取了当前实例的this锁。
     * 如果传入的n < 0，将在add()方法内部调用dec()方法。
     *
     * 由于dec()方法也需要获取this锁，现在问题来了：
     * 对同一个线程，能否在获取到 锁a 以后,继续获取 锁a？
     *
     * 答案是肯定的。这种能被同一个线程反复获取的锁，就叫做可重入锁。
     */
    public synchronized void add(int n) {
        if (n < 0) {
            dec(n);
        } else {
            count += n;
        }
    }

    public synchronized void dec(int n) {
        count += n;
    }

    public int get(){
        return count;
    }
}
