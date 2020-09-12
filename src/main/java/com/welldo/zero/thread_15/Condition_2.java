package com.welldo.zero.thread_15;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition
 *
 * 0.
 * 使用ReentrantLock比直接使用synchronized更安全，可以替代synchronized进行线程同步。
 *
 * synchronized可以配合wait和notify实现线程在条件不满足时等待，条件满足时唤醒，
 * 同样,用ReentrantLock可以使用Condition对象来实现wait和notify的功能。
 *
 *
 * 1.Condition提供的await()、signal()、signalAll()原理和synchronized的wait()、notify()、notifyAll()是一致的
 *      await()会释放当前锁，进入等待状态；
 *      signal()会唤醒某个等待线程；
 *      signalAll()会唤醒所有等待线程；
 *      唤醒线程从await()返回后需要重新获得锁。
 *
 *
 * 2.此外，和tryLock()类似，await()可以在等待指定时间后，如果还没有被其他线程通过signal()或signalAll()唤醒，
 * 可以自己醒来：todo 如何理解"自己醒来'
 *
 * @author welldo
 * @date 2020/9/8
 */
public class Condition_2 {

}

/** {@link Concurrent_5} */
class TaskQueue2 {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private Queue<String> queue = new LinkedList<>();

    public void addTask(String s) {
        lock.lock();
        try {
            queue.add(s);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public String getTask() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                condition.await();
            }
            return queue.remove();
        } finally {
            lock.unlock();
        }
    }

    public String getTask2() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                if (condition.await(1, TimeUnit.SECONDS)) {
                    // 被其他线程唤醒
                } else {
                    // 指定时间内没有被其他线程唤醒, 自己醒来, todo 如何理解
                }
            }
            return queue.remove();
        } finally {
            lock.unlock();
        }
    }
}