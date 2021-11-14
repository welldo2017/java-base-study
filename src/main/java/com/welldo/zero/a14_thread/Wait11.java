package com.welldo.zero.a14_thread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 使用wait和notify
 *
 * 完整例子
 *
 *
 * @author welldo
 * @date 2020/9/8
 */
public class Wait11 {
    public static void main(String[] args) throws InterruptedException {
        TaskQueue11 queue = new TaskQueue11();
        List<Thread> ts = new ArrayList<>();

        //5个线程执行get
        for (int i=0; i<5; i++) {
            Thread t = new Thread() {
                public void run() {
                    // 执行task:
                    while (true) {
                        try {
                            String s = queue.getTask();
                            System.out.println("execute task: " + s);
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                }
            };
            t.start();
            ts.add(t);
        }

        //1个线程执行add, 循环10次,
        Thread add = new Thread(() -> {
            for (int i=1; i<=10; i++) {
                // 放入task:
                String s = "t-" + i;
                System.out.println("add task: " + s);
                queue.addTask(s);
                try { Thread.sleep(100); } catch(InterruptedException e) {}
            }
        });
        add.start();
        add.join();

        Thread.sleep(100);
        for (Thread t : ts) {
            t.interrupt();
        }

    }

}

/**
 * 这个例子中，我们重点关注addTask()方法，内部调用了this.notifyAll()而不是notify()，
 * 使用notifyAll()将唤醒所有当前正在this锁等待的线程，
 * 而notify()只会唤醒其中一个（具体哪个依赖操作系统，有一定的随机性）。
 * 这是因为可能有多个线程正在getTask()方法内部的wait()中等待，使用notifyAll()将一次性全部唤醒。
 * 通常来说，notifyAll()更安全。有些时候，如果我们的代码逻辑考虑不周，用notify()会导致只唤醒了一个线程，而其他线程可能永远等待下去醒不过来了。
 *
 *
 * 但是，注意到wait()方法返回时需要重新获得this锁。
 * 假设当前有5个线程被唤醒，唤醒后，首先要等待执行addTask()的线程结束此方法后，才能释放this锁，
 * 随后，这5个线程中只能有一个获取到this锁，剩下4个将继续等待。
 */
class TaskQueue11 {
    Queue<String> queue = new LinkedList<>();

    public synchronized void addTask(String s) {
        this.queue.add(s);
        this.notifyAll();
    }

    public synchronized String getTask() throws InterruptedException {
        while (queue.isEmpty()) {
            this.wait();
        }
        return queue.remove();
    }
}