package com.welldo.other.list;

import java.util.ArrayList;
import java.util.List;

/**
 * 问题：List 如何一边遍历，一边删除？
 *
 * 针对错误方式，
 * 抛java.util.ConcurrentModificationException异常了，翻译成中文就是：并发修改异常。
 *
 * 查看字节码，由此可以看出，foreach循环在实际执行时，其实使用的是Iterator，使用的核心方法是hasnext()和next()。
 * ArrayList类的Iterator，调用next()方法获取下一个元素时，第一行代码就是调用了checkForComodification();，
 * 而该方法的核心逻辑就是比较modCount和expectedModCount这2个变量的值。
 *
 * 在上面的例子中，刚开始modCount和expectedModCount的值都为3，所以第1次获取元素"zhang3"是没问题的，
 * 但是当执行完下面这行代码时： * list.remove(zhang3);
 * modCount的值就被修改成了4。
 * 所以在第2次获取元素时，modCount和expectedModCount的值就不相等了，所以抛出了java.util.ConcurrentModificationException异常。
 *
 *
 *
 * 既然不能使用foreach来实现，那么我们该如何实现呢？ *
 * 主要有以下3种方法： *
 *  使用Iterator的remove()方法
 *  使用for循环正序遍历
 *  使用for循环倒序遍历
 *
 *
 * @author welldo
 * @date 2020/7/3
 */
public class Foreach {

    public static void main(String[] args) {

        String zhang3 = "zhang3";

        List<String> list = new ArrayList<>();
        list.add("zhang3");
        list.add("li4");
        list.add("wang5");

        //错误的示范
        // for (String s : list) {
        //     if (s.equals(zhang3)) {
        //         list.remove(s);
        //     }
        // }


        //正确方式1
        // Iterator<String> iterator = list.iterator();
        // while (iterator.hasNext()) {
        //     String next = iterator.next();
        //     if (next.equals(zhang3)) {
        //         iterator.remove();
        //     }
        // }



        //正确方式2,修正下标
        // for (int i = 0; i < list.size(); i++) {
        //     String item = list.get(i);
        //     if (item.equals(zhang3)) {
        //         list.remove(i);
        //         i = i-1;
        //     }
        // }


        //正确方式3，倒序遍历，不用修改index
        for (int i = list.size() - 1; i >= 0; i--) {
            String item = list.get(i);
            if (item.equals(zhang3)) {
                list.remove(i);
            }
        }
        System.out.println(list);
    }
}





