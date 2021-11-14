package com.welldo.zero.a20_functional;


/**
 * Stream 是什么
 *
 * 0. Java从8开始，不但引入了Lambda表达式，还引入了一个全新的流式API：Stream API。它位于java.util.stream包中。
 * Stream 是"一个顺序输出的Java对象的序列"
 *
 * 1. Stream和List的区别
 * List存储的每个元素, 都是已经存储在内存中的某个Java对象，
 * Stream它可以“存储”有限个或无限个元素。
 * 这里的存储打了个引号，是因为元素有可能已经全部存储在内存中，也有可能是根据需要实时计算出来的。
 *
 * Stream的另一个特点是，一个Stream可以轻易地转换为另一个Stream，而不是修改原Stream本身。
 * 最后，真正的计算通常发生在最后结果的获取，也就是惰性计算。
 * 惰性计算的特点是：一个Stream转换为另一个Stream时，实际上只存储了转换规则，并没有任何计算发生。
 *
 *
 * @author welldo
 * @date 2020/9/16
 */
public class Stream3 {
}
