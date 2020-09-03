package com.welldo.zero.thread14;

/**
 * 多线程基础
 *
 * 0.多任务 * 就是同时运行多个任务
 * 即使是单核cpu，也可以同时运行多个任务。
 *
 * 假设我们有语文、数学、英语3门作业要做，也就是3个任务，我可以做1分钟语文作业，再做1分钟数学作业，再做1分钟英语作业：
 * 看上去就像同时在做3门作业一样
 *
 * 类似的，操作系统轮流让多个任务交替执行，例如，让浏览器执行0.001秒，让QQ执行0.001秒，再让音乐播放器执行0.001秒，
 * 在人看来，CPU就是在同时执行多个任务。
 *
 * so, 单核cpu实现多任务, 实际上就是CPU对多个任务轮流交替执行。(任务也叫进程)
 *
 *
 * 1.进程
 * 在计算机中，任务也叫进程，浏览器是一个进程，视频播放器是另一个进程, qq是一个进程...
 * 一个进程可以包含一个或多个线程
 *                         ┌──────────┐
 *                         │chrome    │
 *                         │┌────────┐│
 *             ┌──────────┐││ Thread ││┌──────────┐
 *             │word      ││└────────┘││excel     │
 *             │┌────────┐││┌────────┐││┌────────┐│
 * ┌──────────┐││ Thread ││││ Thread ││││ Thread ││
 * │QQ        ││└────────┘││└────────┘││└────────┘│
 * │┌────────┐││┌────────┐││┌────────┐││┌────────┐│
 * ││ Thread ││││ Thread ││││ Thread ││││ Thread ││
 * │└────────┘││└────────┘││└────────┘││└────────┘│
 * └──────────┘└──────────┘└──────────┘└──────────┘
 * ┌──────────────────────────────────────────────┐
 * │               Operating System               │
 * └──────────────────────────────────────────────┘
 * 操作系统调度的最小任务单位, 不是进程，而是线程。
 * 如何调度线程完全由操作系统决定，程序自己不能决定什么时候执行，以及执行多长时间。
 *
 *
 * 2.实现多任务的方法，有以下几种：
 *
 * a. 多进程模式（每个进程只有一个线程）：
 * ┌──────────┐ ┌──────────┐ ┌──────────┐
 * │Process   │ │Process   │ │Process   │
 * │┌────────┐│ │┌────────┐│ │┌────────┐│
 * ││ Thread ││ ││ Thread ││ ││ Thread ││
 * │└────────┘│ │└────────┘│ │└────────┘│
 * └──────────┘ └──────────┘ └──────────┘
 *
 * b.多线程模式（一个进程有多个线程）：
 * ┌────────────────────┐
 * │Process             │
 * │┌────────┐┌────────┐│
 * ││ Thread ││ Thread ││
 * │└────────┘└────────┘│
 * │┌────────┐┌────────┐│
 * ││ Thread ││ Thread ││
 * │└────────┘└────────┘│
 * └────────────────────┘
 *
 * c.多进程＋多线程模式（复杂度最高）：
 * ┌──────────┐┌──────────┐┌──────────┐
 * │Process   ││Process   ││Process   │
 * │┌────────┐││┌────────┐││┌────────┐│
 * ││ Thread ││││ Thread ││││ Thread ││
 * │└────────┘││└────────┘││└────────┘│
 * │┌────────┐││┌────────┐││┌────────┐│
 * ││ Thread ││││ Thread ││││ Thread ││
 * │└────────┘││└────────┘││└────────┘│
 * └──────────┘└──────────┘└──────────┘
 *
 *
 *
 * 3.多线程
 * 一个Java程序实际上是一个JVM进程，JVM进程用一个主线程来执行main()方法，在main()方法内部，我们又可以启动多个线程。
 * 因此，对于大多数Java程序来说，我们说多任务，实际上是说如何使用多线程实现多任务。
 *
 *
 * @author welldo
 * @date 2020/9/2
 */
public class Base1 {
}
