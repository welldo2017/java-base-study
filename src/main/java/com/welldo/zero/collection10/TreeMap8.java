package com.welldo.zero.collection10;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * TreeMap
 * 0.
 * 我们已经知道，HashMap是一种以空间换时间的映射表，它的实现原理决定了内部的Key是无序的，即遍历HashMap的Key时，其顺序是不可预测的
 *
 * 1.
 * 还有一种Map，它在内部会对Key进行排序，这种Map就是SortedMap。注意到SortedMap是接口，它的实现类是TreeMap。
 *        ┌───┐
 *        │Map│
 *        └───┘(接口)
 *          ▲
 *     ┌────┴─────┐
 *     │          │
 * ┌───────┐ ┌─────────┐
 * │HashMap│ │SortedMap│
 * └───────┘ └─────────┘(接口)
 *                ▲
 *                │
 *           ┌─────────┐
 *           │ TreeMap │
 *           └─────────┘(实现类)
 *
 *
 * 2. SortedMap保证遍历时以Key的顺序来进行排序
 *  使用TreeMap时，放入的Key必须实现Comparable接口。String、Integer这些类已经实现了Comparable接口，因此可以直接作为Key使用
 *
 * 3.
 * 如果作为Key的class没有实现Comparable接口，那么，必须在创建TreeMap时, 在构造器中,指定一个自定义排序算法：
 *
 * 4.获取时,
 * 即使key是自定义的class, 也是直接get即可, 不需要覆写equals()和hashCode()，因为TreeMap不使用equals()和hashCode()。
 *
 * 5.
 * !!! TreeMap在比较两个Key是否相等时，依赖Key的compareTo()方法或者Comparator.compare()方法。
 * 在两个Key相等时，必须返回0
 *
 * @author welldo
 * @date 2020/8/21
 */
public class TreeMap8 {

    public static void main(String[] args) {

        //2.
        Map<String, Integer> map = new TreeMap<>();
        map.put("orange", 1);
        map.put("apple", 2);
        map.put("pear", 3);
        for (String key : map.keySet()) {
            System.out.println(key);// 按照字母排序: apple, orange, pear
        }

        //3.
        System.out.println("-----------");
        Map<Person8, Integer> treeMap = new TreeMap<>(new Comparator<Person8>() {
            @Override
            public int compare(Person8 o1, Person8 o2) {
                //调用string的compareTo方法, 按照字母正序(abcd)返回; 如果想按照逆序返回,前面加上负号;
                return o1.name.compareTo(o2.name);
            }
        });
        treeMap.put(new Person8("dick"),1);
        treeMap.put(new Person8("bob"),2);
        treeMap.put(new Person8("crack"),3);
        for (Person8 key : treeMap.keySet()) {
            System.out.println(key);
        }

        //4.
        System.out.println(treeMap.get(new Person8("dick")));

        //5.
        System.out.println("-----------");
        Map<Student8, Integer> map5 = new TreeMap<>(new Comparator<Student8>() {
            public int compare(Student8 p1, Student8 p2) {
                // return p1.score > p2.score ? -1 : 1;//5.1 按照分数排序, 从高到低
                return Integer.compare(p1.score,p2.score); //5.2
            }
        });
        map5.put(new Student8("Tom", 77), 1);
        map5.put(new Student8("Bob", 66), 2);
        map5.put(new Student8("Lily", 99), 3);
        for (Student8 key : map5.keySet()) {
            System.out.println(key);
        }
        //按照5.1的 compare方法, 这里会返回null; 因为, 在两个Key相等时，必须返回0; 此方法没有返回0,所以必然为空
        System.out.println(map5.get(new Student8("Bob", 66)));

        //因为5.2, 只比较了score, 所以name乱写, 也能匹配成功
        System.out.println(map5.get(new Student8("Bobxxx", 66)));
    }
}

//此class, 没有实现comparable接口
class Person8 {
    public String name;
    Person8(String name) {
        this.name = name;
    }
    public String toString() {
        return "{Person: " + name + "}";
    }
}

class Student8 {
    public String name;
    public int score;
    Student8 (String name, int score) {
        this.name = name;
        this.score = score;
    }
    public String toString() {
        return String.format("{%s: score=%d}", name, score);
    }
}
