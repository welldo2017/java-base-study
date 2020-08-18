package com.welldo.zero.generic9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 什么是泛型
 *
 * 泛型就是编写模板代码来适应任意类型；
 * 泛型的好处是使用时不必对类型进行强制转换，它通过编译器对类型进行检查；
 *
 * 1.
 * 泛型的继承关系：
 * 可以把ArrayList<Integer>向上转型为List<Integer>（T不能变！），
 * 不能把ArrayList<Integer>向上转型为ArrayList<Number>（T不能变）
 *
 * 2. 泛型接口
 * jdk提供的排序方法: Arrays.sort(Object[] a)
 * 要求:All elements in the array must implement the {@link Comparable} interface
 *
 * 2.5 如何实现泛型接口?
 *  自定义class, 并实现comparable<T>, 重写compareTo方法即可.
 *
 *
 * @author welldo
 * @date 2020/8/17
 */
public class What1 {
    public static void main(String[] args) {

        //2. Arrays.sort(Object[])可以对任意数组进行排序，但待排序的元素必须实现Comparable<T>这个泛型接口：
        String[] ss = new String[] { "Orange", "Apple", "Pear" };
        Arrays.sort(ss);
        System.out.println(Arrays.toString(ss));

        //2.5
        Person[] ps = new Person[] {
                new Person("Bob", 61),
                new Person("Alice", 88),
                new Person("Lily", 75),
        };
        Arrays.sort(ps);
        System.out.println(Arrays.toString(ps));
    }
}


//2.5 实现泛型接口
class Person implements Comparable<Person> {
    String name;
    int score;
    Person(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public int compareTo(Person other) {
        //按照分数排序
        return this.score - other.score;

        //按照名字排序
        // return this.name.compareTo(other.name);
    }

    public String toString() {
        return this.name + "," + this.score;
    }
}

