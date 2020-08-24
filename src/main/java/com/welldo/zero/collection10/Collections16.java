package com.welldo.zero.collection10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Collections
 * Collections是JDK提供的工具类，同样位于java.util包中。它提供了一系列静态方法，能更方便地操作各种集合。
 *
 *
 * 1.创建空集合
 * Collections提供了一系列方法来创建空集合：要注意到返回的空集合是不可变集合，无法向其中添加或删除元素。
 *  创建空List：List<T> emptyList()
 *  创建空Map：Map<K, V> emptyMap()
 *  创建空Set：Set<T> emptySet()
 *
 *
 * 2. * 创建单元素集合
 * Collections提供了一系列方法来创建一个单元素集合：要注意到返回的单元素集合也是不可变集合，无法向其中添加或删除元素。
 *  创建一个元素的List：List<T> singletonList(T o)
 *  创建一个元素的Map：Map<K, V> singletonMap(K key, V value)
 *  创建一个元素的Set：Set<T> singleton(T o)
 *
 *
 * 2.5
 * 实际上，使用List.of(T...)更方便，因为它既可以创建空集合，也可以创建单元素集合，还可以创建任意个元素的集合：
 * List<String> list1 = List.of(); // empty list
 * List<String> list2 = List.of("apple"); // 1 element
 * List<String> list3 = List.of("apple", "pear"); // 2 elements
 *
 *
 * 3.排序
 * Collections可以对List进行排序。因为排序会直接修改List元素的位置，因此必须传入可变List：
 *
 *
 * 4.Collections提供了洗牌算法:Collections.shuffle(list)
 *
 *
 * 5.不可变集合
 * Collections还提供了一组方法把可变集合封装成不可变集合：
 *  封装成不可变List：List<T> unmodifiableList(List<? extends T> list)
 *  封装成不可变Set：Set<T> unmodifiableSet(Set<? extends T> set)
 *  封装成不可变Map：Map<K, V> unmodifiableMap(Map<? extends K, ? extends V> m)
 *
 *
 * 6.线程安全集合
 * Collections还提供了一组方法，可以把线程不安全的集合变为线程安全的集合：
 * 变为线程安全的List：List<T> synchronizedList(List<T> list)
 * 变为线程安全的Set：Set<T> synchronizedSet(Set<T> s)
 * 变为线程安全的Map：Map<K,V> synchronizedMap(Map<K,V> m)
 * 从Java 5开始，引入了更高效的并发集合类，所以上述这几个同步方法已经没有什么用了。
 *
 * @author welldo
 * @date 2020/8/24
 */
public class Collections16 {
    public static void main(String[] args) {
        /*
        1.此外，也可以用各个集合接口提供的of(T...)方法创建空集合。例如，以下创建空List的两个方法是等价的：
        List<String> list1 = List.of();
        List<String> list1 = Collections.emptyList();
         */
        List<String> list1 = Collections.emptyList();
        // list2.add("hello");//运行时异常:UnsupportedOperationException

        /*
        2.此外，也可以用各个集合接口提供的of(T...)方法创建单元素集合。例如，以下创建单元素List的两个方法是等价的：
        List<String> list1 = List.of("apple");
        List<String> list2 = Collections.singletonList("apple");
         */
        List<String> list2 = Collections.singletonList("apple");
        // list2.add("pear");//运行时异常:UnsupportedOperationException


        /*
        3.必须传入可变List：
         */
        List<String> list3 = new ArrayList<>();
        list3.add("c");
        list3.add("b");
        list3.add("a");
        System.out.println(list3);// 排序前:
        Collections.sort(list3);
        System.out.println(list3);// 排序后:


        /*
        5.然而，继续对原始的可变List进行增删是可以的，并且，会直接影响到封装后的“不可变”
        因此，如果我们希望把一个可变List封装成不可变List，那么，返回不可变List后，最好立刻扔掉可变List的引用
         */
        List<String> mutable = new ArrayList<>();
        mutable.add("apple");
        mutable.add("pear");
        // 变为不可变集合:
        List<String> immutable = Collections.unmodifiableList(mutable);
        // immutable.add("orange"); // UnsupportedOperationException!

        mutable.add("orange");//改变 "可变list,会影响不可变list"
        System.out.println(immutable);

        mutable = null; // 立刻扔掉mutable的引用
    }
}
