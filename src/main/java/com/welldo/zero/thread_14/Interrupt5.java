package com.welldo.zero.thread_14;

/**
 * 中断线程
 *
 * 2. 方法1的补充
 * 如果线程处于等待状态，例如，t.join()会让main线程进入等待状态，此时，如果对main线程调用interrupt()，main线程会立刻抛出InterruptedException，
 * 因此，a线程只要捕获到join()方法抛出的InterruptedException，就说明有其他线程对a线程调用了interrupt()方法，通常情况下该线程应该立刻结束运行。
 *
 *
 * @author welldo
 * @date 2020/9/3
 */
public class Interrupt5 {
    public static void main(String[] args) throws InterruptedException {
        test2();
    }


    static void test2() throws InterruptedException {
        //本方法由 main 线程执行
        MyThread52 thread = new MyThread52();
        thread.start();//开启 thread 线程
        Thread.sleep(1000);//main 线程睡1s

        thread.interrupt();//main 通知 thread 线程 该中断了
        thread.join();//main 线程等待 thread 线程结束

        System.out.println("main线程--end");
    }
}

class MyThread52 extends Thread {
    //本方法由 thread 线程执行
    public void run() {
        Thread hello = new HelloThread42();
        hello.start(); // 启动hello线程
        try {
            // t线程 正在等待, 等待hello线程结束
            // 第80行代码让 t线程 interrupt, 那么t线程会抛出异常,
            // 也就是说 t线程只要捕获到join()方法抛出的InterruptedException，就说明有其他线程对 t线程 调用了interrupt()方法
            hello.join();
        } catch (InterruptedException e) {
            System.out.println("MyThread52 interrupted!");
        }
        hello.interrupt();//如果去掉这一行代码，可以发现hello线程仍然会继续运行，且JVM不会退出。
    }
}

class HelloThread42 extends Thread {
    //本方法由 hello线程 执行
    public void run() {
        int n = 0;
        while (!isInterrupted()) {
            n++;
            System.out.println(n + " hello!");
            try {
                /*
                 * hello线程睡100ms
                 * 为什么要break呢?
                 * 查看 InterruptedException 的注释:
                 * Thrown when a thread is waiting, sleeping, or otherwise occupied, and the thread is interrupted
                 * 某线程被interrupted时, 它恰好在wait,sleep,occupied, 它就会抛出异常, c() 也就不会被正常置为false,
                 * while循环就不能正常中止, 就只能靠break中止.
                 */
                Thread.sleep(100);
            } catch (InterruptedException e) {
                break;
            }
        }
        System.out.println("hello线程--end");
    }
}


