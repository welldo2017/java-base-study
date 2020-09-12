package com.welldo.zero.thread_15;

import com.welldo.zero.thread_14.Synchronized7;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock 读写锁
 *
 * 1.前面讲到的ReentrantLock保证了只有一个线程可以执行临界区代码：
 * 但是有些时候，这种保护有点过头。
 * 因为我们发现，任何时刻，只允许一个线程修改，也就是调用inc()方法是必须获取锁，
 * 但是，get()方法只读取数据，不修改数据，它实际上允许多个线程同时调用。
 * 看代码
 *
 *
 * 2.实际上我们想要的是：允许多个线程同时读; 但只要有一个线程在写，其他线程就必须等待
 * 也就是说, 第一条线程(简称t1), 第2条线程(简称t2) 的交互情况如下
 * +───────+─────────────+────────────────+
 * |       | t2=读        | t2=写          |
 * +=======+=============+================+
 * | t1=读  | 允许         | 不允许         |
 * | t1=写  | 不允许       | 不允许          |
 * +───────+─────────────+────────────────+
 * 使用ReadWriteLock 可以实现
 *
 *
 * 3.看代码
 * 使用ReadWriteLock时，适用条件是同一个数据，有大量线程读取，但仅有少数线程修改。
 *
 * 4.如果我们深入分析ReadWriteLock，会发现它有个潜在的问题：
 * 如果有线程正在读，写线程需要等待读线程释放锁后才能获取写锁，即读的过程中不允许写，这是一种悲观的读锁。
 *
 * @author welldo
 * @date 2020/9/8
 */
public class ReadWriteLock_3 {
}

class Counter31 {
    private final Lock lock = new ReentrantLock();
    private int[] counts = new int[10];

    //1.
    public void inc(int index) {
        lock.lock();
        try {
            counts[index] += 1;
        } finally {
            lock.unlock();
        }
    }

    //1.
    public int[] get() {
        lock.lock();
        try {
            return Arrays.copyOf(counts, counts.length);
        } finally {
            lock.unlock();
        }
    }
}

class Counter33 {
    private final ReadWriteLock rwlock = new ReentrantReadWriteLock();
    private final Lock rlock = rwlock.readLock();
    private final Lock wlock = rwlock.writeLock();
    private int[] counts = new int[10];

    public void inc(int index) {
        wlock.lock(); // 加写锁
        try {
            counts[index] += 1;
        } finally {
            wlock.unlock(); // 释放写锁
        }
    }

    /**
     * 3.
     * 在读取时，多个线程可以同时获得读锁，这样就大大提高了并发读的执行效率。
     *
     * 4.
     * 疑问: 如果对读操作不加锁，效果是否是一样的呢
     * 答案: 必须加锁,锁的目的,是保证连续读逻辑上一致的：
     *
     * int x = obj.x;
     * // 这里线程可能中断
     * int y = obj.y;
     *
     * 假设obj的x，y是[0,1]，某个写线程修改成[2,3]，你读到的要么是[0,1]，要么是[2,3]，
     * 但是没有锁，你读到的可能是[0,3]
     *
     * 请阅读原子操作{@link Synchronized7}
     */
    public int[] get() {
        rlock.lock(); // 加读锁
        try {
            return Arrays.copyOf(counts, counts.length);
        } finally {
            rlock.unlock(); // 释放读锁
        }
    }
}


