package com.welldo.zero.a15_thread;

import com.welldo.zero.a4_oop.core_class.SomeUtil9;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 使用ForkJoin
 *
 * 0.
 * Java 7开始引入了一种新的Fork/Join线程池，它可以执行一种特殊的任务：把一个大任务拆成多个小任务并行执行。
 *
 * 我们举个例子：如果要计算一个超大数组的和，最简单的做法是用一个循环在一个线程内完成：
 * ┌─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┐
 * └─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┘
 *
 * 还有一种方法，可以把数组拆成两部分，分别计算，最后加起来就是最终结果，这样可以用两个线程并行执行：
 * ┌─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┐  * ┌─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┬─┐
 * └─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┘  * └─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┴─┘
 *
 * 如果拆成两部分还是很大，我们还可以继续拆，用4个线程并行执行：
 * ┌─┬─┬─┬─┬─┬─┐  * ┌─┬─┬─┬─┬─┬─┐  * ┌─┬─┬─┬─┬─┬─┐  * ┌─┬─┬─┬─┬─┬─┐
 * └─┴─┴─┴─┴─┴─┘  * └─┴─┴─┴─┴─┴─┘  * └─┴─┴─┴─┴─┴─┘  * └─┴─┴─┴─┴─┴─┘
 *
 * 这就是Fork/Join任务的原理：
 * 判断一个任务是否足够小，如果是，直接计算， * 否则，就分拆成几个小任务分别计算。
 * 这个过程可以反复“裂变”成一系列小任务。
 *
 * 1. 看代码
 * 继承 RecursiveTask or RecursiveAction 并重写computer()方法
 *
 *
 * 2.Fork/Join线程池在Java标准库中就有应用。
 * java.util.Arrays.parallelSort(array)可以进行并行排序，它的原理就是内部通过Fork/Join对大数组分拆进行并行排序，
 * 在多核CPU上就可以大大提高排序的速度。
 *
 * @author welldo
 * @date 2020/9/9
 */
public class ForkJoin_10 {

    /** random相关,请查看 {@link SomeUtil9} */
    static Random random = new Random(0);

    public static void main(String[] args) throws Exception {
        // 创建2000个随机数组成的数组:
        long[] array = new long[2000];
        long expectedSum = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = random();
            expectedSum += array[i];
        }
        System.out.println("Expected sum: " + expectedSum);


        // fork/join:
        ForkJoinTask<Long> task = new SumTask(array, 0, array.length);
        long startTime = System.currentTimeMillis();
        Long result = ForkJoinPool.commonPool().invoke(task);
        long endTime = System.currentTimeMillis();
        System.out.println("Fork/join sum: " + result + " in " + (endTime - startTime) + " ms.");
    }



    //每次得到的数都一样
    static long random() {
        return random.nextInt(10000);
    }
}

class SumTask extends RecursiveTask<Long> {
    static final int THRESHOLD = 500;//500个作为一组,进行计算
    long[] array;
    int start;
    int end;

    SumTask(long[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    /**
     * 在compute()方法中，关键是如何“分裂”出子任务并且提交子任务：
     *
     * 一个大的计算任务0~2000首先分裂为两个小任务0~1000和1000~2000，
     * 这两个小任务仍然太大，继续分裂为更小的0~500，500~1000，1000~1500，1500~2000，
     * 最后，计算结果被依次合并，得到最终结果。
     */
    @Override
    protected Long compute() {
        if (end - start <= THRESHOLD) {
            // 如果任务足够小,直接计算:
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += this.array[i];
            }
            return sum;
        }
        // 任务太大,一分为二:
        int middle = (end + start) / 2;
        System.out.println(String.format("split %d~%d ==> %d~%d, %d~%d", start, end, start, middle, middle, end));
        SumTask subtask1 = new SumTask(this.array, start, middle);
        SumTask subtask2 = new SumTask(this.array, middle, end);

        // invokeAll会并行运行两个子任务:
        invokeAll(subtask1, subtask2);

        // 获得子任务的结果:
        Long subresult1 = subtask1.join();
        Long subresult2 = subtask2.join();

        Long result = subresult1 + subresult2;
        System.out.println("result = " + subresult1 + " + " + subresult2 + " ==> " + result);
        return result;
    }
}
