package com.welldo.zero.a15_thread;

import java.util.concurrent.locks.StampedLock;

/**
 * StampedLock
 *
 * 1. 乐观锁 悲观锁
 * 如果我们深入分析ReadWriteLock，会发现它有个潜在的问题：
 * 如果有线程正在读，写线程需要等待读线程释放锁后才能获取写锁，即读的过程中不允许写，这是一种悲观的读锁。
 *
 * 乐观锁, 意思就是乐观地估计读的过程中大概率不会有写入，
 * 悲观锁, 是读的过程中拒绝有写入，也就是写入必须等待
 * 显然乐观锁的并发效率更高，但一旦有小概率的写入导致读取的数据不一致，需要能检测出来，再读一遍就行。
 *
 * 2.
 * 要进一步提升并发执行效率，Java 8引入了新的读写锁：StampedLock。
 * StampedLock和ReadWriteLock相比，改进之处在于：读的过程中也允许获取写锁并写入！
 * 这样一来，我们需要一点额外的代码来判断读的过程中是否有写入
 * 看代码:
 *
 * 3.可见，StampedLock把读锁细分为乐观读和悲观读，能进一步提升并发效率。
 * 但这也是有代价的：一是代码更加复杂，二是StampedLock是不可重入锁，不能在一个线程中反复获取同一个锁。
 * (写锁没有乐观悲观之分)
 *
 * @author welldo
 * @date 2020/9/9
 */
public class StampedLock_4 {
}


class Point4 {
    private final StampedLock stampedLock = new StampedLock();

    private double x;
    private double y;

    public void move(double deltaX, double deltaY) {
        long stamp = stampedLock.writeLock(); // 获取写锁
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            stampedLock.unlockWrite(stamp); // 释放写锁
        }
    }

    //2.
    public double distanceFromOrigin() {
        long stamp = stampedLock.tryOptimisticRead(); // 获得一个乐观读锁, 并返回版本号
        // 注意下面两行代码不是原子操作
        // 假设x,y = (100,200)
        double currentX = x; // 此处已读取到x=100，但x,y可能被写线程修改为(300,400)
                            //非原子, so这里可能会被cpu暂停
        double currentY = y; // 此处已读取到y，如果无修改，读取是正确的(100,200)// 如果有修改，读取是错误的(100,400)

        //验证版本号, 如果在读取过程中没有写入，版本号不变，验证成功返回true，我们就可以放心地继续后续操作。
        // 如果在读取过程中有写入，版本号变化，验证失败。我们再通过获取 "悲观读锁" 再次读取
        if (!stampedLock.validate(stamp)) {
            stamp = stampedLock.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally {
                stampedLock.unlockRead(stamp); // 释放悲观读锁
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }
}
