package com.welldo.zero.a10_collection;

/**
 *
 * 集合就是“由若干个确定的元素所构成的整体”。例如，5只小兔构成的集合：
 * ┌ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ┐
 * │   (\_(\     (\_/)     (\_/)     (\_/)      (\(\   │
 *     ( -.-)    (•.•)     (>.<)     (^.^)     (='.')
 * │  C(")_(")  (")_(")   (")_(")   (")_(")   O(_")")  │
 * └ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ┘
 *
 * 1.
 * 既然Java提供了数组这种数据类型，可以充当集合，那么，我们为什么还需要其他集合类？
 * 这是因为数组有如下限制：
 *      数组初始化后大小不可变；
 *      数组只能按索引顺序存取。
 *
 * 2.
 * Java的java.util包主要提供了以下三种类型的集合：
 *   a.Collection
 *      |--- List(接口)：一种有序的,可以重复的元素的集合;
 *      |--- Set(接口)：一种无序的,没有重复元素的集合;
 *   b.Map(接口)：一种通过键值（key-value）查找的映射表集合;
 *      |--- sortedMap(接口)：key有序的集合;
 *
 *             +---------------+                +------------+
 *             |collection 接口 |                | Map接口     |
 *             +-----+---------+                +-----+------+
 *                   |                                |
 *           +-------+-----+                  +-------+------+
 *           |             |                  |              |
 *           |             |                  v              v
 *  +--------+--+      +---+---+          +---+------+   +---+--------+
 *  | List      |      |Set    |          | HashMap  |   | SortedMap  |
 *  | 接口       |      | 接口   |          |          |   | 接口        |
 *  +-----+-----+      +-------+          +----------+   +----+-------+
 *        |                                                   |
 *        |                                                   |
 * +------+-----+                                             v
 * |            |                                         +---+-------+
 * |ArrayList   |                                         | TreeMap   |
 * +------------+                                         |           |
 *                                                        +-----------+
 *
 * 3.
 * 由于Java的集合设计非常久远，中间经历过大规模改进，我们要注意到有一小部分集合类是遗留类，不应该继续使用：
 *      Hashtable：一种线程安全的Map实现；
 *      Vector：一种线程安全的List实现；
 *      Stack：基于Vector实现的LIFO的栈。
 *
 * 4.
 * Java访问集合总是通过统一的方式——迭代器（Iterator）来实现，它最明显的好处在于无需知道集合内部元素是按什么方式存储的。
 *
 * @author welldo
 * @date 2020/8/18
 */
public class Collection1 {
}
