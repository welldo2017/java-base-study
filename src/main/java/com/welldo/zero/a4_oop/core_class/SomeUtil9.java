package com.welldo.zero.a4_oop.core_class;

import java.util.Random;

/**
 * 常用工具类
 *
 * Java的核心库提供了大量的现成的类供我们使用。本节我们介绍几个常用的工具类。
 *
 * 1. 求绝对值： * Math.abs(-100); // 100
 *
 * 2. 取最大或最小值： * Math.max(100, 99); // 100
 *
 * 3. 计算x^y次方： * Math.pow(2, 10); // 2的10次方=1024
 *
 * 4. 计算√x： * Math.sqrt(2); // 1.414...
 *
 * 5. 计算以10为底的对数： * Math.log10(100); // 2
 * 6. 计算以e为底的对数： * Math.log(4); // 1.386...
 *
 * 7. Math还提供了几个数学常量：
 *      double pi = Math.PI; // 3.14159...
 *      double e = Math.E; // 2.7182818...
 *
 * 8. 生成一个随机数x，范围是[0,1)： * Math.random(); // 0.53907... 每次都不一样
 *      Random用来创建伪随机数。
 *      所谓伪随机数，是指只要给定一个初始的种子，产生的随机数序列是完全一样的。
 *      如果不给定种子，就使用系统当前时间戳作为种子，因此每次运行时，种子(时间)不同，得到的伪随机数序列就不同。
 *      Random r = new Random(种子);
 *
 *      SecureRandom * 有伪随机数，就有真随机数。实际上真正的真随机数只能通过量子力学原理来获取, 这里就不研究了
 *
 * @author welldo
 * @date 2020/8/11
 */
public class SomeUtil9 {
    public static void main(String[] args) {

        //如果我们要生成一个区间在[MIN, MAX)的随机数，可以借助Math.random()实现，
        double x = Math.random(); // x的范围是[0,1)
        double min = 10;
        double max = 50;
        double y = x * (max - min) + min; // y的范围是[10,50)
        long n = (long) y; // n的范围是[10,50)的整数
        System.out.println(y);
        System.out.println(n);


        System.out.println("************");
        //使用时间戳
        Random r = new Random();
        r.nextInt(); // 2071575453,每次都不一样
        r.nextInt(10); // 5,生成一个[0,10)之间的int
        r.nextLong(); // 8811649292570369305,每次都不一样
        r.nextFloat(); // 0.54335...生成一个[0,1)之间的float
        r.nextDouble(); // 0.3716...生成一个[0,1)之间的double

        System.out.println("************");
        //如果我们在创建Random实例时指定一个种子，就会得到完全确定的随机数序列：
        Random r1 = new Random(222);
        System.out.println(r1.nextInt(100));//无论运行多少次,都是71
    }
}
