package com.welldo.zero.thread_15;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.*;

/**
 * 使用Future
 *
 *
 * 0.
 * 在执行多个任务的时候，使用Java标准库提供的线程池是非常方便的。
 * 我们提交的任务只需要实现Runnable接口，就可以让线程池去执行：
 *
 * Runnable接口有个问题，它的方法没有返回值。
 * 如果任务需要一个返回结果，那么只能保存到变量，还要提供额外的方法读取，非常不便。
 * 所以，Java标准库还提供了一个Callable接口，和Runnable 接口比，它多了一个返回值：
 *
 * 1.
 * Callable接口是一个泛型接口，可以返回指定类型的结果。
 * 现在的问题是，如何获得异步执行的结果？
 * 如果仔细看ExecutorService.submit()方法，可以看到，它返回了一个Future类型，
 * 一个Future类型的实例代表一个未来能获取结果的对象：
 *
 *
 * 2.当我们提交一个Callable任务后，我们会同时获得一个Future对象，
 * 然后，我们在主线程某个时刻调用Future对象的get()方法，就可以获得异步执行的结果。
 * 在调用get()时，如果异步任务已经完成，我们就直接获得结果。
 * 如果异步任务还没有完成，那么get()会阻塞，直到任务完成后才返回结果。
 *
 * 一个Future<V>接口表示一个未来可能会返回的结果，它定义的方法有：
 *      get()：获取结果（可能会等待）
 *      get(long timeout, TimeUnit unit)：获取结果，但只等待指定的时间；
 *      cancel(boolean mayInterruptIfRunning)：取消当前任务；
 *      isDone()：判断任务是否已完成。
 *
 *
 *
 * @author welldo
 * @date 2020/9/9
 */
public class Future_8 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(4);
        Future<BigDecimal> future = es.submit(new Task81("601857"));
        System.out.println(future.get());
        es.shutdown();
    }
}


class Task80 implements Runnable {
    private String result;

    public void run() {
        //这里进行运算
        //这里进行运算
        //运算结果赋值给 result
        this.result = "this is result from runnable";
    }
}

class Task81 implements Callable<BigDecimal> {
    public Task81(String code) {
    }

    @Override
    public BigDecimal call() throws Exception {
        Thread.sleep(1000);
        double d = 5 + Math.random() * 20;
        return new BigDecimal(d).setScale(2, RoundingMode.DOWN);
    }
}
