package com.welldo.zero.collection10;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 编写equals
 *
 * 如何正确编写equals()方法？equals()方法要求我们必须满足以下条件：
 *      自反性（Reflexive）：对于非null的x来说，x.equals(x)必须返回true；
 *      对称性（Symmetric）：对于非null的x和y来说，如果x.equals(y)为true，则y.equals(x)也必须为true；
 *      传递性（Transitive）：对于非null的x、y和z来说，如果x.equals(y)为true，y.equals(z)也为true，那么x.equals(z)也必须为true；
 *      一致性（Consistent）：对于非null的x和y来说，只要x和y状态不变，则x.equals(y)总是一致地返回true或者false；
 *
 *      对null的比较：即x.equals(null)永远返回false。
 *
 *
 * 上述规则看上去似乎非常复杂，但其实代码实现equals()方法是很简单的
 *
 * @author welldo
 * @date 2020/8/20
 */
public class Equals4 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        /*
        注意一个问题，我们往List中添加的"C"和调用contains("C")传入的"C"是不是同一个实例？
        因为我们传入的是new String("C")，所以一定是不同的实例。结果仍然符合预期，这是为什么呢？

        因为List内部并不是通过 == 判断两个元素是否相等，而是使用 equals() 方法判断两个元素是否相等

        因此，想要正确使用List的contains()、indexOf()这些方法，
        放入的实例必须正确覆写equals()方法，否则，放进去的实例，查找不到
         */
        System.out.println(list.contains("C")); // true
        System.out.println(list.contains(new String("C")));
        System.out.println(list.indexOf("C")); // 2
        System.out.println(list.indexOf(new String("C"))); //

    }
}

 class Person4 {
    public String name;
    public int age;

     @Override
     public boolean equals(Object o) {
         if (this == o) return true;
         if (o == null || getClass() != o.getClass()) return false;
         Person4 person4 = (Person4) o;
         return age == person4.age && Objects.equals(name, person4.name);
     }

    
 }
