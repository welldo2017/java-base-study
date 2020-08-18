package com.welldo.zero.generic9;

/**
 * super通配符
 *
 * 2. 通过"super 通配符" 实现只写
 *
 * 3. 对比extends和super通配符
 * 作为方法参数，<? extends T>类型和<? super T>类型的区别在于：
 * <? extends T>允许调用读方法T get()获取T的引用，但不允许调用写方法set(T)传入T的引用（传入null除外）；
 * <? super T>允许调用写方法set(T)传入T的引用，但不允许调用读方法T get()获取T的引用（获取Object除外）。
 *
 * 一个是允许读不允许写，另一个是允许写不允许读。
 *
 *
 * 4.
 * 我们来看Java标准库的Collections类定义的copy()方法：它的作用是把一个List的每个元素依次添加到另一个List中。
 * public class Collections {
 *     // 把src的每个元素复制到dest中:
 *     public static <T> void copy(List<? super T> dest, List<? extends T> src) {
 *         for (int i=0; i<src.size(); i++) {
 *             T t = src.get(i);
 *             dest.add(t);
 *         }
 *     }
 * }
 *
 * 它的第一个参数是List<? super T>，表示目标List，
 * 第二个参数List<? extends T>，表示要复制的List。
 *
 * 在for循环中，我们可以看到，对于类型<? extends T>的变量src，我们可以安全地获取类型T的引用，
 * 而对于类型<? super T>的变量dest，我们可以安全地传入T的引用。
 *
 * 这个copy()方法的定义就完美地展示了extends和super的意图：
 * copy()方法内部不会读取dest，因为不能调用dest.get()来获取T的引用；
 * copy()方法内部也不会修改src，因为不能调用src.add(T)。
 *
 * 这个copy()方法的另一个好处是可以安全地把一个List<Integer>添加到List<Number>，但是无法反过来添加：
 *
 *
 * 5.PECS原则
 * 何时使用extends，何时使用super？
 * 为了便于记忆，我们可以用PECS原则：Producer Extends Consumer Super。
 * 即：如果需要返回T，它是生产者（Producer），要使用extends通配符；
 * 如果需要写入T，它是消费者（Consumer），要使用super通配符。
 *
 * 还是以Collections的copy()方法为例：
 * public class Collections {
 *     public static <T> void copy(List<? super T> dest, List<? extends T> src) {
 *         for (int i=0; i<src.size(); i++) {
 *             T t = src.get(i); // src是producer
 *             dest.add(t); // dest是consumer
 *         }
 *     }
 * }
 * 需要返回T的src是生产者，因此声明为List<? extends T>，需要写入T的dest是消费者，因此声明为List<? super T>。
 *
 *
 * 6.
 *
 *
 * @author welldo
 * @date 2020/8/18
 */
public class WildCard5 {
    public static void main(String[] args) {

        //1.
        Pair5<Number> p1 = new Pair5<>(1.23, 4.56);
        Pair5<Integer> p2 = new Pair5<>(123, 456);

        setUpdate(p1,100,200);
        setUpdate(p2,300,400);
        System.out.println(p1.getFirst() + ", " + p1.getLast());
        System.out.println(p2.getFirst() + ", " + p2.getLast());

        //2.
        Integer x = p2.getFirst();
    }


    /**
     * 2. 我们无法使用Integer类型来接收getFirst()的返回值，
     * 因为如果传入的实际类型是Pair<Number>，编译器无法将Number类型转型为Integer
     * 注意：Number是一个抽象类，我们无法直接实例化它。但是，即便Number不是抽象类，这里仍然无法通过编译。
     *
     * 换句话说，使用<? super Integer>通配符作为方法参数，表示方法内部代码对于参数只能写，不能读。
     */
    static void get(Pair5<? super Integer> p) {
        //我们无法使用Integer类型来接收getFirst()的返回值，
        // Integer x = p.getFirst();

        //唯一可以接收getFirst()方法返回值的是Object类型：
        Object first = p.getFirst();
    }


    /**
     * 1.1 改写
     * 方法参数接受所有泛型类型为Integer或Integer父类的Pair类型。
     */
    static void setUpdate(Pair5<? super Integer> p, Integer first, Integer last) {
        p.setFirst(first);
        p.setLast(last);
    }


    /**
     * 1. 传入Pair<Integer>是允许的，但是传入Pair<Number>是不允许的。
     * 和extends通配符相反，
     * 这次，我们希望接受Pair<Integer>类型，以及Pair<Number>、Pair<Object>，也就是父类, 父类的父类
     */
    void set(Pair5<Integer> p, Integer first, Integer last) {
        p.setFirst(first);
        p.setLast(last);
    }
}

class Pair5<T> {
    private T first;
    private T last;

    public Pair5(T first, T last) {
        this.first = first;
        this.last = last;
    }

    public T getFirst() {
        return first;
    }
    public T getLast() {
        return last;
    }


    public void setFirst(T first) {
        this.first = first;
    }
    public void setLast(T last) {
        this.last = last;
    }
}