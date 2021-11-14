package com.welldo.zero.a10_collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * List
 *
 * 1.使用数组，在添加和删除元素的时候，会非常不方便。
 * 例如，从一个已有的数组{'A', 'B', 'C', 'D', 'E'}中删除索引为2的元素：
 * ┌───┬───┬───┬───┬───┬───┐
 * │ A │ B │ C │ D │ E │   │
 * └───┴───┴───┴───┴───┴───┘
 *               │   │
 *           ┌───┘   │
 *           │   ┌───┘
 *           │   │
 *           ▼   ▼
 * ┌───┬───┬───┬───┬───┬───┐
 * │ A │ B │ D │ E │   │   │
 * └───┴───┴───┴───┴───┴───┘
 * 这个“删除”操作, 实际上是把'C'后面的元素依次往前挪一个位置，
 * 而“添加”操作, 实际上是把指定位置以后的元素都依次向后挪一个位置，腾出来的位置给新加的元素。
 * 这两种操作，用数组实现非常麻烦。
 *
 * 2.因此，在实际应用中，需要增删元素的有序列表，我们使用最多的是 ArrayList。
 * 实际上，ArrayList在内部使用了 数组 来存储所有元素。
 *
 * 2.5 例如，一个ArrayList拥有5个元素，
 * ┌───┬───┬───┬───┬───┬───┐
 * │ A │ B │ C │ D │ E │   │
 * └───┴───┴───┴───┴───┴───┘
 * 当添加元素F 到index =2 时,先移动, 再插入
 * ┌───┬───┬───┬───┬───┬───┐
 * │ A │ B │   │ C │ D │ E │
 * └───┴───┴───┴───┴───┴───┘
 * ┌───┬───┬───┬───┬───┬───┐
 * │ A │ B │ F │ C │ D │ E │
 * └───┴───┴───┴───┴───┴───┘
 * 可见，ArrayList把添加和删除的操作封装起来，让我们操作List类似于操作数组，却不用关心内部元素如何移动。
 *
 * 3.
 * LinkedList 通过“链表”也实现了List接口。
 * 在LinkedList中，它的内部每个元素都指向下一个元素：
 *         ┌───┬───┐   ┌───┬───┐   ┌───┬───┐   ┌───┬───┐
 * HEAD ──>│ A │ ●─┼──>│ B │ ●─┼──>│ C │ ●─┼──>│ D │   │
 *         └───┴───┘   └───┴───┘   └───┴───┘   └───┴───┘
 *
 * 4.
 * 我们来比较一下ArrayList和LinkedList：
 *
 * +─────────────────+─────────────────+──────────────────────+
 * |                 | arrayList       | LinkedList           |
 * +=================+=================+======================+
 * | 获取指定元素       | 很快(通过索引)    | 很慢(从头开始,依次查找)  |
 * | 添加元素到末尾     | 很快             | 很快                  |
 * | 指定位置增 / 删    | 很慢, 需要移动元素 | 很快, 更改指向即可      |
 * | 内存             | 少               | 大                   |
 * +─────────────────+────────────+───────────────────────────+
 * 通常情况下，我们总是优先使用ArrayList。
 *
 *
 * 5.创建List,
 * List<Integer> list = List.of(1, 2, 5);//jdk14新方法
 *
 *
 * 6.遍历list
 * --for循环, 不推荐
 *      一是代码复杂，二是因为get(int)方法只有ArrayList的实现是高效的，
 *      换成LinkedList后，索引越大，访问速度越慢。
 *
 * --迭代器Iterator(推荐)
 *      Iterator本身也是一个对象，但它是由List的实例调用iterator()方法的时候创建的
 *      不同的List类型，返回的Iterator对象实现也是不同的，但总是具有最高的访问效率。
 *
 * --增强for循环(强烈推荐)
 *      内部封装了迭代器Iterator, 使用更方便;
 *
 *
 * 7.List和Array转换
 * list --> array
 *      --7.1 toArray()方法* 直接返回一个Object[]数组：这种方法会丢失类型信息，所以实际应用很少。
 *      --7.2 toArray( T[] )方法, 传入一个类型相同,长度相同(???)的空数组,
 *          如果你是杠精,指定长度偏不相同, *
 *          如果传入的数组不够大，那么List内部会创建一个新的刚好够大的数组，填充后返回；
 *          如果传入的数组比List元素还要多，那么填充完元素后，剩下的数组元素一律填充null。
 *
 * array --> list
 *      --7.3 List.of(array);   //jdk14
 *      --7.4 Arrays.asList(T...)   // <jdk11
 *      它返回的是一个只读List：调用add()、remove()方法会抛出UnsupportedOperationException
 *
 *
 * @author welldo
 * @date 2020/8/19
 */
public class List2 {
    public static void main(String[] args) {
        //5.
        // List<Integer> list = List.of(1, 2, 5);

        //6.
        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("pear");
        list.add("banana");
        Iterator<String> it = list.iterator();
        for (; it.hasNext(); ) {
            String s = it.next();
            System.out.println(s);
        }

        //6.
        System.out.println("------------");
        for (String s : list) {
            System.out.println(s);
        }

        //7.1
        System.out.println("------------");
        Object[] objects = list.toArray();
        for (Object object : objects) {
            System.out.println(object);
        }


        /*
        7.2
        如果你是杠精,指定长度偏不相同, *
        如果传入的数组不够大，那么List内部会创建一个新的刚好够大的数组，填充后返回；
        如果传入的数组比List元素还要多，那么填充完元素后，剩下的数组元素一律填充null。

        实际上，最常用的是传入一个“恰好”大小的数组：
        Integer[] array = list.toArray(new Integer[list.size()]);

        最后一种更简洁的写法是通过List接口定义的T[] toArray(IntFunction<T[]> generator)方法：
        Integer[] array = list.toArray(Integer[]::new);
        这种函数式写法我们会在后续讲到。
         */
        System.out.println("------------");
        String[] strings = list.toArray(new String[3]);
        for (String s : strings) {
            System.out.println(s);
        }

        //7.3
        Integer[] array = { 1, 2, 3 };
        List<Integer> list7 = Arrays.asList(array);//注意, 返回的是 只读list
        // list7.add(12); //会抛异常 UnsupportedOperationException
        System.out.println(list7);
    }
}
