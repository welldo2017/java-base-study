package com.welldo.zero.generic9;

/**
 * 编写泛型....
 *
 * 1.
 * 通常来说，泛型类一般用在集合类中，例如ArrayList<T>，我们很少需要编写泛型类。
 * 如果我们确实需要编写一个泛型类，那么，应该如何编写它？
 * 可以按照以下步骤来编写一个泛型类。
 *
 * a. 首先，按照某种类型，例如：String，来编写类：
 * public class Pair1 {
 *     private String first;
 *     private String last;
 *     public Pair1(String first, String last) {
 *         this.first = first;
 *         this.last = last;
 *     }
 *     public String getFirst() { *         return first; *     }
 *     public String getLast() { *         return last; *     }
 * }
 *
 * b. 然后，标记所有的特定类型，这里是String：
 * public class Pair1 {
 *     private *** first;
 *     private *** last;
 *     public Pair1(*** first, *** last) {
 *         this.first = first;
 *         this.last = last;
 *     }
 *     public *** getFirst() { *         return first; *     }
 *     public *** getLast() { *         return last; *     }
 * }
 *
 * c. 最后，把特定类型String替换为T，并申明<T>：
 * public class Pair1<T> {
 *     private T first;
 *     private T last;
 *     public Pair1(T first, T last) {
 *         this.first = first;
 *         this.last = last;
 *     }
 *     public T getFirst() { *         return first; *     }
 *     public T getLast() { *         return last; *     }
 * }
 *
 * d. 熟练后即可直接从T开始编写。
 *
 *
 * 2.!!!特别注意，泛型类型<T>不能用于静态方法
 *
 * 4.多个泛型类型
 * 泛型还可以定义多种类型。例如，我们希望Pair不总是存储两个类型一样的对象，就可以使用类型<T, K>：
 *
 * @author welldo
 * @date 2020/8/17
 */
public class Custom2 {
    public static void main(String[] args) {
        //4. 使用多个泛型类型
        Pair2<String, Integer> test = new Pair2<>("test", 123);
    }
}

class Pair1<T> {
    private T first;
    private T last;
    public Pair1(T first, T last) {
        this.first = first;
        this.last = last;
    }
    public T getFirst() {
        return first;
    }
    public T getLast() {
        return last;
    }


    /**
     * 2.特别注意，     * 我们无法在静态方法 的方法参数和返回类型上使用泛型类型T。
     *
     * 解决办法: 在static修饰符后面加一个<T>
     * static Pair1<T> create(T first, T last)
     * 变成
     * static <T> Pair1<T> create(T first, T last)
     *
     * !!! 但实际上，这个<T>和Pair<T>类型的<T>已经没有任何关系了。
     *
     * todo 没有明白用途
     */
    public static <T> Pair1<T> create(T first, T last) {
        return new Pair1<T>(first, last);
    }


    /**
     * 3.
     * 对于静态方法，我们可以单独改写为“泛型”方法，只需要使用另一个类型即可。
     * 对于上面的create()静态方法，我们应该把它改为另一种泛型类型，例如，<K>：
     *
     * 也就是说, 静态泛型方法应该使用其他类型区分:
     * 这样才能清楚地将静态方法的泛型类型和实例类型的泛型类型区分开。
     */
    public static <K> Pair1<K> create2(K first, K last) {
        return new Pair1<K>(first, last);
    }
}


//4.
class Pair2<T, K> {
    private T first;
    private K last;
    public Pair2(T first, K last) {
        this.first = first;
        this.last = last;
    }
}