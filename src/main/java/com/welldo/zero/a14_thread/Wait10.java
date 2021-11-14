package com.welldo.zero.a14_thread;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 使用wait和notify
 *
 * 1.
 * 在Java程序中，synchronized解决了多线程竞争的问题。
 * 但是synchronized并没有解决多线程协调的问题。
 * 看代码
 *
 *
 * 2.如何解决?
 * 如果深入思考一下，我们想要的执行效果是：
 * 线程调用getTask(), 从队列中获取任务。如果队列为空，则getTask()应该等待，直到队列中至少有一个任务时再返回。
 * 因此，多线程协调运行的原则就是：当条件不满足时，线程进入等待状态；当条件满足时，线程被唤醒，继续执行任务。
 *
 * 我们先改造getTask()方法为 getTaskUpdate() 方法
 *
 * 3. 完整例子, 看下一节
 *
 * @author welldo
 * @date 2020/9/8
 */
public class Wait10 {
    public static void main(String[] args) {
        // test11();
        // test12();
        test2();
    }


    /**
     * 按照test12的逻辑, 我们先获取task, 再插入task, 看是否会死循环.
     */
    static void test2() {
        TaskQueue10 queue = new TaskQueue10();

        //线程1 先获取
        Runnable runnable2 = () -> {
            String task = queue.getTaskUpdate();
            System.out.println(task);
        };
        Thread thread2 = new Thread(runnable2);
        thread2.start();

        //线程2 再放入
        Runnable runnable1 = () -> queue.addTaskUpdate("task 2 ");
        Thread thread1 = new Thread(runnable1);
        thread1.start();
    }


    /**
     * 12.这段代码有问题
     * getTask()先判断队列是否为空，如果为空，就循环等待，直到另一个线程往队列中放入了一个任务，while()循环退出，就可以返回队列的元素了。
     * 但实际上while()循环永远不会退出。
     * 因为线程在执行while()循环时，已经在getTask()入口获取了this锁，
     * 其他线程根本无法调用addTask()，因为addTask()执行条件也是获取this锁。
     *
     * 因此，执行此代码，线程会在getTask()中因为死循环而100%占用CPU资源。
     * 解决方案,看2
     */
    static void test12() {
        TaskQueue10 queue = new TaskQueue10();

        //线程1 先获取
        Runnable runnable2 = () -> {
            String task = queue.getTask();
            System.out.println(task);
        };
        Thread thread2 = new Thread(runnable2);
        thread2.start();

        //线程2 再放入
        Runnable runnable1 = () -> queue.addTask("task 12 ");
        Thread thread1 = new Thread(runnable1);
        thread1.start();
    }

    /**
     * 11.这段代码不会有问题, 先放入, 再获取
     */
    static void test11() {
        TaskQueue10 queue = new TaskQueue10();
        //线程1 先放入
        Runnable runnable1 = () -> queue.addTask("task 11 ");
        Thread thread1 = new Thread(runnable1);
        thread1.start();

        //线程2 获取
        Runnable runnable2 = () -> {
            String task = queue.getTask();
            System.out.println(task);
        };
        Thread thread2 = new Thread(runnable2);
        thread2.start();
    }

}

/**
 * 对于一个任务管理器，多个线程同时往队列中添加任务，可以用synchronized加锁：
 */
class TaskQueue10 {
    Queue<String> queue = new LinkedList<>();

    //1.
    public synchronized void addTask(String s) {
        this.queue.add(s);
    }

    //1.
    public synchronized String getTask() {
        while (queue.isEmpty()) {
        }
        return queue.remove();
    }


    /**
     * 2.改造后的代码     * 在条件不满足时，线程进入等待状态：
     *
     * 当一个线程执行到 getTaskUpdate()方法内部时，它必定已经获取到了this锁，如果while条件成立,线程将执行this.wait()，进入等待状态。
     * 这里的关键是：wait()方法必须在当前获取的锁对象上调用，这里获取的是this锁，因此调用this.wait()。
     *
     * 调用wait()方法后，线程进入等待状态，wait()方法不会返回(相当于阻塞在这里)，
     * 直到将来某个时刻，线程从等待状态被其他线程唤醒后，wait()方法才会返回， 然后，继续往下执行.
     *
     * 有些仔细的童鞋会指出：即使线程在getTask()内部等待，其他线程如果拿不到this锁，照样无法执行addTask()，肿么办？
     * 这个问题的关键就在于wait()方法的执行机制非常复杂。
     * 1. 它不是一个普通的Java方法，而是由JVM用C语言实现的native方法
     * 2. 必须在synchronized块中才能调用wait()方法，因为wait()方法调用时，会释放线程获得的锁(也就是this)，
     *      wait()方法返回后，线程又会重新试图获得锁。
     */
    public synchronized String getTaskUpdate()  {
        while (queue.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return queue.remove();
    }

    /**
     * 2. 当一个线程在this.wait()等待时，它就会释放this锁，从而使得其他线程能够在 addTaskUpdate()方法获得this锁。
     *
     * 现在我们面临第二个问题：如何让等待的线程被重新唤醒，然后从wait()方法返回？
     * 答案是在相同的锁对象上调用notify()方法。
     */
    public synchronized void addTaskUpdate(String s) {
        this.queue.add(s);
        this.notify(); // 唤醒在this锁等待的线程
    }
}
