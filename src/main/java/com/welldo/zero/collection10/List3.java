package com.welldo.zero.collection10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 练习题
 * 1. 给定一组连续的整数，例如：10，11，12，……，20，但其中缺失一个数字，试找出缺失的数字：
 * 2. 增强版：和上述题目一样，但整数不再有序，试找出缺失的数字：
 *
 * @author welldo
 * @date 2020/8/19
 */
public class List3 {
    public static void main(String[] args) {
        // 构造从start到end的序列：
        final int start = 10;
        final int end = 20;
        List<Integer> list = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            list.add(i);
        }

        //题目2需要加上这一行
        // Collections.shuffle(list); // 洗牌算法shuffle可以随机交换List中的元素位置:

        // 随机删除List中的一个元素:
        int removed = list.remove((int) (Math.random() * list.size()));

        int found = findMissingNumber12(start, end, list);//题目1的查找方法

        System.out.println(list.toString());
        System.out.println("消失的数字: " + removed);
        System.out.println("计算出的数字: " + found);
        System.out.println(removed == found ? "测试成功" : "测试失败");
    }

    /**
     * 题目2的查找方法
     *
     */
    static int findMissingNumber21(int start, int end, List<Integer> list) {
        //todo
        return 0;
    }


    /**
     * 题目1 解法2
     * 解法1的升级版, 不用优先判断第一个和最后一个.
     */
    static int findMissingNumber12(int start, int end, List<Integer> list) {
        Iterator<Integer> iterator = list.iterator();
        for (; start != end; start++) {
            if (start != iterator.next().intValue()) {
                return start;
            }
        }
        return start;
    }


    /**
     * 题目1,解法1
     * 垃圾算法, 有n个元素,就会计算n次
     * i2-i1 = 1;
     * i3-i2 = 1;
     * ....
     * i9-i8 = 1
     * 其中, im-in !=1时,说明m和n中间被取走了数字
     * 特殊情况,收尾两个数字被取走,则会导致计算无效  * 所以先判断收尾两个数字
     */
    static int findMissingNumber11(int start, int end, List<Integer> list) {
        if (!list.contains(start)) {
            return start;
        }
        if (!list.contains(end)) {
            return end;
        }

        for (int i = 0; i < list.size(); i++) {
            int result = (list.get(i + 1) - list.get(i));
            if (result != 1) {
                return list.get(i+1)-1;
            }
        }
        return 0;
    }
}
