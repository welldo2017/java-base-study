package com.welldo.zero.thread_15;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用ReentrantLock
 *
 * 1.
 * 从Java 5开始，引入了一个高级的处理并发的java.util.concurrent包，它提供了大量更高级的并发功能，能大大简化多线程程序的编写。
 * 我们知道Java语言直接提供了synchronized关键字用于加锁，但这种锁一是很重，二是获取时必须一直等待，没有额外的尝试机制。
 * java.util.concurrent.locks包提供的 ReentrantLock 用于替代 synchronized 加锁
 *
 * 2. 传统的synchronized 与 ReentrantLock对比
 *
 * 3.和synchronized不同的是，ReentrantLock可以尝试获取锁：
 *
 *
 * @author welldo
 * @date 2020/9/8
 */
public class ReentrantLock_1 {
}


class Counter1 {
    private final Lock lock = new ReentrantLock();
    private int count;


    /**
     * 2.传统的synchronized 写法
     * 因为synchronized是Java语言层面提供的语法，所以我们不需要考虑异常
     */
    public void add(int n) {
        synchronized(this) {
            count += n;
        }
    }


    /**
     * 2.ReentrantLock写法
     * 而ReentrantLock是Java代码实现的锁，我们就必须先获取锁，然后在finally中正确释放锁。
     */
    public void addUpdate(int n) {
        lock.lock();
        try {
            count += n;
        } finally {
            lock.unlock();
        }
    }


    /**
     * 3. 尝试获取锁的时候，最多等待1秒。如果1秒后仍未获取到锁，tryLock()返回false，
     * 程序就可以做一些额外处理，而不是无限等待下去。
     *
     * 所以，使用ReentrantLock比直接使用synchronized更安全，线程在tryLock()失败的时候不会导致死锁。
     */
    public void addUpdate3(int n) throws InterruptedException {
        if (lock.tryLock(1, TimeUnit.SECONDS)) {
            try {
                count += n;
            } finally {
                lock.unlock();
            }
        }

        //do something
    }
}