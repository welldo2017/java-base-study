package com.welldo.other.map;


import java.util.*;

/**
 * 双层for循环的替代方案
 * 1.使用map      （效率最高）
 * 2.使用stream   (效率其实一般般，这里不演示。)
 *
 *
 * @author welldo
 * @date 2020/8/6
 */
public class MapAndFor {
    public static void main(String[] args) throws InterruptedException {
        String name = "sdflasjfajf";

        List<Member> list1 = new ArrayList<>();
        List<Member> list2 = new ArrayList<>();


        for (int i = 0; i < 30000; i++) {
            Date date = new Date();
            //list1，3w个元素，每个元素有名字
            list1.add(new Member((i + 1), name, (i + 1), date));

            //list2，1.5w个元素，每个元素都没有名字
            if (i % 2 == 0) {
                list2.add(new Member((i + 1), null, (i + 1), date));
            }
        }


        //方案1
        //双for循环嵌套+break，（如果不加break，更耗时）
        long start2 = System.currentTimeMillis();
        int forNumber2 = 0;
        for (Member m2 : list2) {
            for (Member m1 : list1) {
                if (m1.getId().intValue() == m2.getId().intValue()) {
                    m2.setName(m1.getName());
                    forNumber2 ++;
                    break;
                }
            }
        }
        long end2 = System.currentTimeMillis();
        System.out.println("双for循环+break，查询时间为：" + (end2 - start2) + "(毫秒)，一共查询出" + forNumber2 + "条数据 \n\n\n");


        //方案3
        //map查询测试
        long start3 = System.currentTimeMillis();
        int mapNumber = 0;

        //给map赋值
        Map<Integer, Member> map = new HashMap<>();
        for (Member m1 : list1) {
            map.put(m1.getId(), m1);
        }

        //从map中进行匹配。
        for (Member m2 : list2) {
            Member m = map.get(m2.getId());
            m2.setName(m.getName());
            mapNumber++;
        }
        long end3 = System.currentTimeMillis();
        System.out.println("使用map结构查询时间为：" + (end3 - start3) + "(毫秒)，一共查询出" + mapNumber + "条数据 \n\n\n");
    }
}

