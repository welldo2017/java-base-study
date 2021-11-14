package com.welldo.zero.collection10;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 编写equals和hashCode
 *
 * 1.* Map<String, Person> map = new HashMap<>();
 * map.put("a", new Person("Xiao Ming"));
 * map.put("b", new Person("Xiao Hong"));
 * map.put("c", new Person("Xiao Jun"));
 *
 * map.get("a"); // Person("Xiao Ming")
 * map.get("x"); // null
 *
 * HashMap之所以能根据key直接拿到value，原因是它内部通过空间换时间的方法，用一个大数组存储所有value，
 * 并根据key直接计算出value应该存储在数组的哪个索引上：
 *   ┌───┐
 * 0 │   │
 *   ├───┤
 * 1 │ ●─┼───> Person("Xiao Ming") //如果key的值为"a"，计算得到的索引总是1，因此返回value为Person("Xiao Ming")
 *   ├───┤
 * 2 │   │
 *   ├───┤
 * 3 │   │
 *   ├───┤
 * 4 │   │
 *   ├───┤
 * 5 │ ●─┼───> Person("Xiao Hong") //如果key的值为"b"，计算得到的索引总是5，因此返回value为Person("Xiao Hong")
 *   ├───┤
 * 6 │ ●─┼───> Person("Xiao Jun")
 *   ├───┤
 * 7 │   │
 *   └───┘
 *
 * 2.
 * 同样, 存入时,key是字符串"a"; 取出时,用字符串"a"; 但两个"a"不是同一个对象;
 * 见代码
 *
 *
 * 3.
 * 我们再思考一下HashMap为什么能通过key直接计算出value存储的索引?
 * 相同的key对象（使用equals()判断时返回true）必须要计算出相同的索引，否则，相同的key每次取出的value就不一定对。
 * 通过key计算索引的方式,就是调用key对象的hashCode()方法，它返回一个int整数(也就是key对应的value的索引),继而直接返回value。
 *
 * 因此，正确使用Map必须保证：
 *      作为key的对象必须正确覆写equals()方法，相等的两个key实例调用equals()必须返回true；
 *      作为key的对象还必须正确覆写hashCode()方法，且hashCode()方法要严格遵循以下规范：
 *          * 如果两个对象相等，则两个对象的hashCode()必须相等；
 *          * 如果两个对象不相等，则两个对象的hashCode()尽量不要相等。
 *      即对应两个实例a和b：
 *          * 如果a和b相等，那么a.equals(b)一定为true，则a.hashCode()必须等于b.hashCode()；
 *          * 如果a和b不相等，那么a.equals(b)一定为false，则a.hashCode()和b.hashCode()尽量不要相等。
 *
 *
 * 延伸阅读
 * 4. * hashCode()返回的int范围高达±21亿，先不考虑负数，HashMap内部使用的数组得有多大？
 *
 * 实际上HashMap初始化时默认的数组大小只有16，任何key，无论它的hashCode()有多大，
 * 都可以简单地通过： int index = key.hashCode() & 0xf 把索引确定在0～15，即永远不会超出数组范围 * // 0xf = 15
 *
 * hashmap长度超过了负载因子, 会在内部自动扩容，每次*2(即长度16变为长度32)，
 * 相应地，需要对每个key重新确定hashCode()计算的索引位置: int index = key.hashCode() & 0x1f; // 0x1f = 31
 *
 * 由于扩容会导致重新分布已有的key-value，所以，频繁扩容对HashMap的性能影响很大
 *
 * 如果我们确定要使用一个容量为10000个key-value的HashMap，更好的方式是创建HashMap时就指定容量：
 * Map<String, Integer> map = new HashMap<>(10000);
 * 虽然指定容量是10000，但HashMap内部的数组长度总是2^n，因此，实际数组长度被初始化为比10000大的16384（2^14）。
 *
 *
 * 5.如果不同的两个key，例如"a"和"b"，它们的hashCode()恰好是相同的, 那么，当我们放入：
 * map.put("a", new Person("Xiao Ming"));
 * map.put("b", new Person("Xiao Hong"));
 * 时，由于计算出的数组索引相同，后面放入的"Xiao Hong"会不会把"Xiao Ming"覆盖了？
 *
 * 当然不会！
 * 使用Map的时候，只要key不相同，它们映射的value就互不干扰。
 * 但是，不同的key，映射到相同的hashCode()，即相同的数组索引上，肿么办？
 *
 * 那么，在HashMap的数组中，实际存储的不是一个Person实例，而是一个List，它包含两个Entry，一个是"a"的映射，一个是"b"的映射：
 * 在查找的时候，例如：Person p = map.get("a"); 先找到List, 再找到key="a"的Entry, 返回对应的Person实例。
 *   ┌───┐
 * 0 │   │
 *   ├───┤
 * 1 │   │
 *   ├───┤
 * 2 │   │
 *   ├───┤
 * 3 │   │
 *   ├───┤
 * 4 │   │
 *   ├───┤
 * 5 │ ●─┼───> List<Entry<String, Person>>
 *   ├───┤
 * 6 │   │
 *   ├───┤
 * 7 │   │
 *   └───┘
 *
 *
 * 6.
 * 我们把不同的key具有相同的hashCode()的情况称之为哈希冲突
 * 在冲突的时候，一种最简单的解决办法是用List存储hashCode()相同的key-value。
 * 显然，如果冲突的概率越大，这个List就越长，Map的get()方法效率就越低
 *
 *
 *
 * @author welldo
 * @date 2020/8/20
 */
public class EqualsAndHashCode6 {
    public static void main(String[] args) {
        /*
        2.
        同样, 在Map的内部，对key做比较是通过equals()实现的，
        这一点和List查找元素需要正确覆写equals()是一样的，
        即, 正确使用Map必须保证：作为key的对象必须正确覆写equals()方法。

        我们经常使用String作为key，因为String已经正确覆写了equals()方法。
        但如果我们放入的key是一个自己写的类，就必须保证正确覆写了equals()方法。
         */
        String key1 = "a";//在常量池
        String key2 = new String("a");//在堆内存, 所以key1,key2 肯定不是同一个对象

        Map<String, Integer> map = new HashMap<>();
        map.put(key1, 123);
        System.out.println(map.get(key2));//123, 能正确获取

        System.out.println(key1 == key2); // false
        System.out.println(key1.equals(key2)); // true
    }
}



class Person6 {
    String firstName;
    String lastName;
    int age;

    /**
     * 编写equals()和hashCode()遵循的原则是：
     *
     * equals()用到的用于比较的每一个字段，都必须在hashCode()中用于计算；
     * equals()中没有使用到的字段，绝不可放在hashCode()中计算。
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person6 person6 = (Person6) o;
        return age == person6.age &&
                Objects.equals(firstName, person6.firstName) &&
                Objects.equals(lastName, person6.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age);
    }
}
