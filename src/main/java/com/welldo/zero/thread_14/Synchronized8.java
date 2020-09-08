package com.welldo.zero.thread_14;

import com.welldo.zero.oop_4.base.Lambda15;

/**
 * 同步方法
 *
 * 1. 对上一节的改进
 * 让线程自己选择锁对象往往会使得代码逻辑混乱，也不利于封装。更好的方法是把synchronized逻辑封装起来。
 * 例如，我们编写一个计数器：
 *
 * 2.
 * 如果一个类被设计为允许多线程访问, 且正确访问，我们就说这个类就是“线程安全”的（thread-safe），
 *
 * Java标准库的java.lang.StringBuffer也是线程安全的。
 * 还有一些不变类，例如String，Integer，LocalDate，它们的所有成员变量都是final，多线程同时访问时只能读不能写，这些不变类也是线程安全的。
 * 最后，类似Math这些只提供静态方法，没有成员变量的类，也是线程安全的。
 *
 * 3. 和1等效的另一种写法
 *
 * @author welldo
 * @date 2020/9/7
 */
public class Synchronized8 {
    public static void main(String[] args) throws InterruptedException {
        Counter8 c1 = new Counter8();
        Counter8 c2 = new Counter8();

        /**  lambda 写法, 请参考 {@link Lambda15}  */
        Thread thread1 = new Thread(() -> c1.add(10_000));
        Thread thread2 = new Thread(() -> c1.dec(10_000));
        Thread thread3 = new Thread(() -> c2.add(10_000));
        Thread thread4 = new Thread(() -> c2.dec(10_000));
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();

        System.out.println(c1.get());
        System.out.println(c2.get());
    }

}

/**
 * 2.这样设计, 这个类就是线程安全的。
 */
class Counter8 {
    private int count = 0;

    /**
     * 1.
     * 这样一来，线程调用add()、dec()方法时，它不必关心同步逻辑，因为synchronized代码块在add()、dec()方法内部。
     * 并且，我们注意到，synchronized锁住的对象是this，
     * 即当前实例，这又使得创建多个Counter实例的时候，它们之间互不影响，可以并发执行：
     */
    public void add(int n) {
        synchronized(this) {
            count += n;
        }
    }


    public void dec(int n) {
        synchronized(this) {
            count -= n;
        }
    }


    /**
     * 3.
     * 当我们锁住的是this实例时，实际上可以用 synchronized 修饰这个方法
     * 所以, add() 与 add1() 写法等效
     */
    public synchronized void add1(int n) { // 锁住this
        count += n;
    } // 解锁

    public int get() {
        return count;
    }
}