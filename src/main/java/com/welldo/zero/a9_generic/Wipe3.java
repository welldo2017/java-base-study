package com.welldo.zero.a9_generic;

/**
 * 泛型擦除（Type Erasure）
 *
 * 擦拭法是指，虚拟机对泛型其实一无所知，所有的工作都是编译器做的。
 *
 * 1.
 * 例如，我们编写了一个泛型类Pair<T>，这是编译器看到的代码：
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
 *
 * 而虚拟机根本不知道泛型。这是虚拟机执行的代码：
 * public class Pair1 {
 *     private Object first;
 *     private Object last;
 *     public Pair1(Object first, Object last) {
 *         this.first = first;
 *         this.last = last;
 *     }
 *     public Object getFirst() { *         return first; *     }
 *     public Object getLast() { *         return last; *     }
 * }
 *
 * 因此，Java使用擦拭法实现泛型，导致了：
 * 编译器把类型<T>视为Object；
 * 编译器根据<T>实现安全的强制转型。
 *
 * 2.也就是说
 * 我们编写的代码, 是这样的:
 *      Pair1<String> p = new Pair1<>("Hello", "world");
 *      String first = p.getFirst();
 *      String last = p.getLast();
 * 编译器编译后的代码是这样的
 *      Pair1 p = new Pair1("Hello", "world");
 *      String first = (String) p.getFirst();
 *      String last = (String) p.getLast();
 * !!! 编译器内部永远把所有类型T视为Object处理，但是，在需要转型的时候，编译器会根据T的类型自动为我们实行安全地强制转型。
 *
 *
 * 3.局限性1
 * <T> 不能是基本数据类型, 因为基本数据类型无法转成Object
 *
 * 4.局限2,
 * 无论T的类型是什么，getClass()返回同一个Class实例，。
 *
 * 5.局限3 * 泛型字段, 不能实例化
 *  因为, 如果能写, 比如: * first = new T();
 * 擦拭后实际上变成了： * first = new Object();
 * 这样一来，创建new Pair1<String>()和创建new Pair1<Integer>()就全部成了Object，显然编译器要阻止这种类型不对的代码。
 *
 *
 *
 *
 * @author welldo
 * @date 2020/8/17
 */
public class Wipe3 {
    public static void main(String[] args) {

        //4.对Pair<String>和Pair<Integer>类型获取Class时，因为编译后它们全部都是Pair<Object>,
        // 所以, 获取到的是同一个Class，也就是Pair类的Class
        Pair3<String> p1 = new Pair3<>("hello","world");
        Pair3<Integer> p2 = new Pair3<>(1, 2);

        Class<? extends Pair3> c1 = p1.getClass();
        Class<? extends Pair3> c2 = p2.getClass();
        Class<Pair3> c3 = Pair3.class;
        System.out.println(c1 == c2);
        System.out.println(c1 == c3);
    }
}

class Pair3<T> {
    private T first;
    private T last;
    private String name;

    public Pair3(T first, T last) {

        this.first = first;
        this.last = last;

        //5. 泛型字段, 不能实例化
        // this.last = new T();

        //5. 非泛型字段, 可以实例化
        this.name = new String();
    }

    public T getFirst() {
        return first;
    }

    public T getLast() {
        return last;
    }
}