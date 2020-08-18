package com.welldo.zero.generic9;

/**
 * extends通配符
 *
 * 1. 上限通配符
 * 这种使用<? extends Number>的泛型定义称之为上界通配符（Upper Bounds Wildcards），即把泛型类型T的上界限定在Number了。
 *
 *
 * 2. 通过"extends 通配符" 实现只读限制
 *
 *
 *
 * @author welldo
 * @date 2020/8/18
 */
public class WildCard4 {


    public static void main(String[] args) {
        Pair4<Integer> p = new Pair4<>(123, 456);

        //1.2 需要传入的类型是(Number, Number)，实际参数类型是(Integer, Integer)。
        //从多态的角度来讲,，引用类型是Number =  实际类型是Integer, 是没有问题的.
        // 问题在于方法参数类型定死了只能传入Pair<Number>。
        // int n = add(p);


        //1.3 有没有办法使得方法参数接受Pair<Integer>？
        // 使用Pair<? extends Number>, 使得方法接收所有泛型类型为Number或Number子类的Pair类型。
        // 这样一来，给方法传入Pair<Integer>类型时，它符合参数Pair<? extends Number>类型
        int i = addUpdate(p);
        System.out.println(i);
    }


    /**
     * 1.1 接收参数为Pair4的实例, 且泛型为 Number
     */
    static int add(Pair4<Number> p) {
        Number first = p.getFirst();
        Number last = p.getLast();
        return first.intValue() + last.intValue();
    }

    /**
     * 1.3     * 为了和 add方法做个对比, 就不在add()方法上改了.
     * (上限通配符)
     */
    static int addUpdate(Pair4<? extends Number> p) {
        Number first = p.getFirst();
        Number last = p.getLast();
        return first.intValue() + last.intValue();
    }


    /**
     * 2.通过"通配符" 实现只读限制
     */
    static int add2(Pair4<? extends Number> p) {
        //读取正常, 即返回值是Number或Number的子类，因此，可以安全赋值给Number类型的变量
        Number first = p.getFirst();
        Number last = p.getLast();

        /*
        重新赋值,出现错误.
        编译错误发生在p.setFirst()传入的参数是Integer类型。
        为什么不能传入Integer? 尝试一下,会发现, double, flout等,都不支持, 也就是说, 所有类型都不能传,

        原因还在于擦拭法。如果我们传入的p是Pair<Double>,
        显然它满足参数定义Pair<? extends Number>，然而，Pair1<Double>的setFirst()显然无法接受Integer类型
        其他类型同理.

        这是一个对参数 Pair4<? extends Number> p 进行只读的方法（恶意调用set(null)除外
         */
        // p.setFirst(new Integer(100));
        // p.setLast(new Integer(100));
        return p.getFirst().intValue() + p.getFirst().intValue();
    }
}

class Pair4<T> {
    private T first;
    private T last;

    public Pair4(T first, T last) {
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