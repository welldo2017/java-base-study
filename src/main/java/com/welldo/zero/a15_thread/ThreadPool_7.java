package com.welldo.zero.a15_thread;

import java.util.concurrent.*;

/**
 * 使用线程池
 *
 * 0.
 * Java内置了多线程支持，启动一个新线程非常方便，但是，创建线程需要操作系统的资源，频繁创建和销毁大量线程需要消耗大量时间。
 *
 * 1.
 * 我们可以把很多小任务让一组线程来执行，而不是一个任务对应一个新线程。这种能接收大量小任务,并进行分发处理的就是线程池。
 * 简单地说，线程池内部维护了若干个线程.
 *
 * 没有任务的时候，这些线程都处于等待状态。
 * 如果有N个新任务，就分配N个空闲线程执行。
 * 如果所有线程都处于忙碌状态，新任务要么放入队列等待，要么增加一个新线程进行处理。
 *
 * 2.
 * Java标准库提供了ExecutorService接口表示线程池，
 * ExecutorService只是接口，Java标准库提供的几个常用实现类有：
 *      Executors.FixedThreadPool：线程数固定的线程池；
 *      Executors.CachedThreadPool：线程数根据任务动态调整的线程池；
 *      Executors.SingleThreadExecutor：仅单线程执行的线程池。
 * 看代码
 *
 * 3.CachedThreadPool
 *
 * 4.如果我们想把线程池的大小限制在4～10个之间动态调整怎么办?（推荐这种方式）
 *
 * 5.ScheduledThreadPool
 * 还有一种任务，需要定期反复执行，例如，每秒刷新证券价格。
 * 这种任务本身固定，需要反复执行的，可以使用ScheduledThreadPool。放入ScheduledThreadPool的任务可以定期反复执行。
 *
 * 注意FixedRate和FixedDelay的区别。
 * FixedRate是指任务总是以固定时间间隔触发，不管任务执行多长时间：
 * │░░░░   │░░░░░░ │░░░    │░░░░░  │░░░
 * ├───────┼───────┼───────┼───────┼────>
 * │<─────>│<─────>│<─────>│<─────>│
 *
 * 而FixedDelay是指，上一次任务执行完毕后，等待固定的时间间隔，再执行下一次任务：
 * │░░░│       │░░░░░│       │░░│       │░
 * └───┼───────┼─────┼───────┼──┼───────┼──>
 *     │<─────>│     │<─────>│  │<─────>│
 *
 *
 * 5.5思考
 * 在FixedRate模式下，假设每秒触发，如果某次任务执行时间超过1秒，后续任务会不会并发执行？
 *      If any execution of this task takes longer than its period, then subsequent executions may start late, but will not concurrently execute.
 *      译：如果此任务的任何执行时间超过其周期，则后续执行可能会延迟开始，但不会并发执行。
 *
 * 如果任务抛出了异常，后续任务是否继续执行？(看源码)
 *      If any execution of the task encounters an exception, subsequent executions are suppressed.
 *      译：如果任务的任何执行遇到异常，则将禁止后续任务的执行
 *
 *
 * 6.Java标准库还提供了一个java.util.Timer类，(旧api)
 * 这个类也可以定期执行任务，但是，一个Timer会对应一个Thread，所以，一个Timer只能定期执行一个任务，多个定时任务必须启动多个Timer，
 * 而一个ScheduledThreadPool就可以调度多个定时任务，所以，我们完全可以用ScheduledThreadPool取代旧的Timer。
 *
 *
 * @author welldo
 * @date 2020/9/9
 */
public class ThreadPool_7 {
    /**
     * 2.观察执行结果，一次性放入6个任务，
     * 由于线程池只有固定的4个线程，因此，前4个任务会同时执行，等到有线程空闲后，才会执行后面的两个任务。
     */
    public static void main(String[] args) {
        // test2();
        // test3();
        // test4();
        test5();
    }

    /**
     * 3.如果我们把线程池改为 CachedThreadPool，由于这个线程池的实现会根据任务数量动态调整线程池的大小，
     * 所以6个任务可一次性全部同时执行。
     *
     * 想创建指定动态范围的线程池，可以这么写：
     */
    static void test3() {
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 6; i++) {
            es.submit(new Task72("" + i));
        }
        es.shutdown();
    }

    /**
     * 4.如果我们想把线程池的大小限制在4～10个之间动态调整怎么办？
     *
     * ！！！《阿里巴巴Java开发手册》推荐这种方式
     * 查看Executors.newCachedThreadPool()方法的源码，其实也调用了ThreadPoolExecutor构造器。
     *
     * keepAliveTime: 当线程数大于核心时，这是多余空闲线程在终止前等待新任务的最长时间。
     */
    static void test4() {
        int min = 4;
        int max = 10;
        ExecutorService es = new ThreadPoolExecutor(min, max,
                60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

        for (int i = 0; i < 6; i++) {
            es.submit(new Task72("" + i));
        }
        es.shutdown();
    }

    /**
     * 5.
     * 反复执行, 所以没有shutdown
     */
    static void test5() {
        ScheduledExecutorService es = Executors.newScheduledThreadPool(4);
        System.out.println("main thread start....");
        // 1秒后执行一次性任务:
        // es.schedule(new Task72("one-time"), 1, TimeUnit.SECONDS);


        // 2秒后开始执行定时任务，每3秒执行:
        // es.scheduleAtFixedRate(new Task72("fixed-rate"), 2, 3, TimeUnit.SECONDS);

        //如果任务以固定的3秒为间隔执行，我们可以这样写：
        //2秒后开始执行定时任务，以3秒为间隔执行:
        es.scheduleWithFixedDelay(new Task72("fixed-delay"), 2, 3, TimeUnit.SECONDS);
    }


    /**
     * 2.
     * 关闭线程池.  线程池在程序结束的时候要关闭。
     * 使用shutdown()方法关闭线程池的时候，它会等待正在执行的任务先完成，然后再关闭。
     * shutdownNow()会立刻停止正在执行的任务，
     * awaitTermination(long timeout, TimeUnit unit)则会等待指定的时间让线程池关闭。
     *
     * ！！！《阿里巴巴Java开发手册》规定
     * 线程池不允许使用 Executors 去创建，而是通过 ThreadPoolExecutor 的方式，这样让你更加明确线程池的运行规则，规避资源耗尽的风险。
     * 说明：Executors 返回的线程池对象的弊端如下：
     * 1） FixedThreadPool 和 SingleThreadPool：
     *     允许的请求队列长度为 Integer.MAX_VALUE，可能会堆积大量的请求，从而导致 OOM。
     * 2） CachedThreadPool：
     *     允许的创建线程数量为 Integer.MAX_VALUE，可能会创建大量的线程，从而导致 OOM。
     */
    static void test2() {
        ExecutorService es = Executors.newFixedThreadPool(4); // 创建一个固定大小的线程池:
        for (int i = 0; i < 6; i++) {
            es.submit(new Task72("" + i));
        }
        es.shutdown();
    }
}

class Task72 implements Runnable{

    private String taskName = "";

    public Task72(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println("start..."+taskName);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        System.out.println("end..."+taskName);
    }
}
