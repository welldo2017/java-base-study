package com.welldo.zero.a10_collection;

/**
 *
 * Java的集合类都可以使用for each循环，List、Set和Queue会迭代每个元素，Map会迭代每个key。
 *
 * 1.
 * List<String> list = List.of("Apple", "Orange", "Pear");
 * for (String s : list) {
 *     System.out.println(s);
 * }
 *
 * 实际上，Java编译器并不知道如何遍历List。上述代码能够编译通过，只是因为编译器把for each循环通过Iterator改写为了普通的for循环：
 * for (Iterator<String> it = list.iterator(); it.hasNext(); ) {
 *      String s = it.next();
 *      System.out.println(s);
 * }
 * 这里的关键在于，集合类通过调用iterator()方法，返回一个Iterator对象，这个对象必须自己知道如何遍历该集合。
 *
 *
 * 2.
 * Iterator是一种抽象的数据访问模型。使用Iterator模式进行迭代的好处有：
 *      对任何集合都采用同一种访问模型；
 *      调用者对集合内部结构一无所知；
 *      集合类返回的Iterator对象知道如何迭代。
 *
 * @author welldo
 * @date 2020/8/24
 */
public class Iterator15 {

}
