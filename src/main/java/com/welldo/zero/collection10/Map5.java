package com.welldo.zero.collection10;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用Map
 *
 * 0.
 * 和List类似，Map也是一个接口，最常用的实现类是HashMap。
 *
 * 1.
 * 使用List, 查找起来, 存在效率非常低的问题，因为平均需要扫描一半的元素才能确定，
 * 而Map这种键值（key-value）映射表的数据结构，作用就是能高效通过key快速查找value（元素）。
 *
 * 2.
 * Map<K, V>是一种键-值映射表，
 * 当我们调用put(K key, V value)方法时，就把key和value做了映射并放入Map。
 * 当我们调用V get(K key)时，就可以通过key获取到对应的value。如果key不存在，则返回null。
 *
 *
 * 3.对同一个key调用两次put()方法，分别放入不同的value，会有什么问题呢
 * 不会有任何问题, value2 会"冲掉" value1,
 *
 * 实际上，put()方法的签名是V put(K key, V value)，
 * 如果放入的key已经存在，put()方法会返回被删除的旧的value，否则(也就是第一次放入)，返回null。
 *
 *
 *
 * @author welldo
 * @date 2020/8/20
 */
public class Map5 {

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        Integer apple = map.put("apple", 123);
        System.out.println(apple);

        Integer apple1 = map.put("apple", 789);
        System.out.println(apple1);
    }



}
