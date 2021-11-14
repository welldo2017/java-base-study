package com.welldo.zero.a15_thread;

import com.welldo.zero.a14_thread.Base1;

/**
 * 使用ThreadLocal
 *
 * 0.
 * 多线程是Java实现多任务( {@link Base1} )的基础
 * Thread对象代表一个线程，我们可以在代码中调用Thread.currentThread()获取当前线程, 看代码
 *
 * 1.
 * 对于0. 中涉及到的n个线程, 组成一个任务, Java标准库提供的线程池可以方便地执行此任务，同时复用线程。
 * Web应用程序就是典型的多任务应用，每个用户请求页面时，我们都会创建一个任务，类似：
 *      public void process(User user) {
 *          checkPermission();
 *          doWork();
 *          saveStatus();
 *          sendResponse();
 *      }
 *
 * 观察process()方法，它内部需要调用若干其他方法，同时，我们遇到一个问题：如何在一个线程内传递状态？
 * process()方法需要传递的状态就是User实例。有的童鞋会想，简单地传入User就可以了：
 *      public void process(User user) {
 *          checkPermission(user);
 *          doWork(user);       //此方法,又调用4个方法
 *          saveStatus(user);   //此方法,又调用3个方法
 *          sendResponse(user);
 *      }
 *
 * 但是往往一个方法又会调用其他很多方法，这样会导致User传递到所有地方.
 * 这种在一个线程中，横跨若干方法调用，需要传递的对象，我们通常称之为上下文（Context），它是一种状态，可以是用户身份、任务信息等。
 *
 *
 * 2. Java标准库提供了一个特殊的 ThreadLocal，它可以在一个线程中传递同一个对象。
 * ThreadLocal实例, 通常总是以静态字段初始化,如下：
 *       static ThreadLocal<User> threadLocalUser = new ThreadLocal<>();
 *
 * 它的典型使用方式如下：(先设置,使用完毕再移除)
 * 通过设置一个User实例关联到ThreadLocal实例中，在移除之前，所有方法都可以随时获取到该User实例：
 * void processUser(user) {
 *     try {
 *         threadLocalUser.set(user);
 *         step1();
 *         step2();
 *     } finally {
 *          //!!!! 特别注意ThreadLocal一定要在finally中清除：
 *          //这是因为当前线程执行完代码后，很可能会被重新放入线程池中，如果没有清除，该线程执行其他代码时，会把上一次的状态带进去。
 *          //看 第四条
 *          threadLocalUser.remove();
 *     }
 * }
 *
 * void step1() {
 *     User u = threadLocalUser.get();
 *     //业务代码
 * }
 * void step2() {
 *     User u = threadLocalUser.get();
 *     //业务代码
 * }
 * 普通的方法调用一定是同一个线程执行的，所以，step1()、step2()，threadLocalUser.get()获取的User对象是同一个实例。
 *
 * 3.
 * ThreadLocal相当于给每个线程都开辟了一个独立的存储空间，各个线程的ThreadLocal关联的实例互不干扰。
 *
 *
 * 4. 为了保证能释放ThreadLocal关联的实例，我们可以通过AutoCloseable接口配合try (resource)  {...}结构，
 * 让编译器自动为我们关闭。例如，一个保存了当前用户名的ThreadLocal可以封装为一个UserContext对象：
 * 看代码
 *
 * @author welldo
 * @date 2020/9/9
 */
public class ThreadLocal_11 {

    //0.
    public static void main(String[] args) {
        log("main start....");
        new Thread(() -> {  log("my task1"); }).start();
        new Thread(() -> {  log("my task2"); }).start();
        new Thread(() -> {  log("my task3"); }).start();
        log("main end....");


        //4.
        User11 welldo = new User11("welldo", 25);

        try (UserContext11 userContext = new UserContext11(welldo)){
            User11 user11 = UserContext11.currentUser();

        }// 在此自动调用UserContext.close()方法释放ThreadLocal关联对象
    }

    static void log(String s){
        System.out.println(Thread.currentThread().getName()+" : " +s);
    }
}

//4.
class UserContext11 implements AutoCloseable {
    private static ThreadLocal<User11> userCtx = new ThreadLocal<>();

    public UserContext11(User11 user) {
        userCtx.set(user);
    }

    public static User11 currentUser(){
        return userCtx.get();
    }

    @Override
    public void close()  {
        userCtx.remove();
    }
}

class User11 {
    String username;
    int id;

    public User11(String username, int id) {
        this.username = username;
        this.id = id;
    }
}
