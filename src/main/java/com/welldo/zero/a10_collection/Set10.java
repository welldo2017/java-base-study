package com.welldo.zero.a10_collection;

import java.util.*;

/**
 * 使用Set
 * {@link Collection1}
 *
 * 1.
 * 我们知道，Map用于存储key-value的映射，对于充当key的对象，是不能重复的，并且，不但需要正确覆写equals()方法，还要正确覆写hashCode()方法。
 * 如果我们只需要存储不重复的key，并不需要存储映射的value，那么就可以使用Set。
 *
 * 2.
 * Set实际上相当于只存储key、不存储value的Map。我们经常用Set用于去除重复元素。
 * 因为放入Set的元素和Map的key类似，都要正确实现equals()和hashCode()方法，否则该元素无法正确地放入Set。
 * 最常用的Set实现类是HashSet，实际上，HashSet仅仅是对HashMap的一个简单封装，
 *
 *
 * 3.
 * Set接口并不保证有序，而SortedSet接口则保证元素是有序的：
 * HashSet是无序的， * TreeSet是有序的 * 用一张图表示：
 *        ┌───┐
 *        │Set│接口
 *        └───┘
 *          ▲
 *     ┌────┴─────┐
 * ┌───────┐ ┌─────────┐
 * │HashSet│ │SortedSet│接口
 * └───────┘ └─────────┘
 *                ▲
 *           ┌────┴────┐
 *           │ TreeSet │实现类
 *           └─────────┘
 *
 * 4. treeSet
 * 使用TreeSet和使用TreeMap的要求一样，添加的元素必须正确实现Comparable接口，
 * 如果没有实现Comparable接口，那么创建TreeSet时必须传入一个Comparator对象。
 * 对key的要求, 与TreeMap 一致 {@link TreeMap8}
 *
 *
 *
 *
 * @author welldo
 * @date 2020/8/24
 */
public class Set10 {
    public static void main(String[] args) {
        //输出的顺序既不是添加的顺序，也不是String排序的顺序
        Set<String> set = new HashSet<>();
        set.add("apple");
        set.add("banana");
        set.add("pear");
        set.add("orange");
        for (String s : set) {
            System.out.println(s);
        }
        System.out.println("******************");

        //4. string 已经实现了comparable接口
        Set<String> treeSet = new TreeSet<>();
        treeSet.add("apple");
        treeSet.add("banana");
        treeSet.add("pear");
        treeSet.add("orange");
        for (String s : treeSet) {
            System.out.println(s);
        }
        System.out.println("******************");

        //5.
        List<Message> received = new ArrayList<>();
        received.add(new Message(2, "发工资了吗？"));
        received.add(new Message(2, "发工资了吗"));
        received.add(new Message(3, "去哪吃饭？"));
        received.add(new Message(3, "去哪吃饭"));
        received.add(new Message(1, "Hello!"));
        received.add(new Message(1, "Hello"));
        received.add(new Message(4, "Bye"));
        received.add(new Message(4, "Bye"));

        List<Message> displayMessages = process(received);
        for (Message message : displayMessages) {
            System.out.println(message.text);
        }

    }

    //5.
    static List<Message> process(List<Message> received) {
        TreeSet<Message> messages = new TreeSet<>(new Comparator<Message>() {
            @Override
            public int compare(Message o1, Message o2) {
                return Integer.compare(o1.sequence,o2.sequence);
            }
        });
        messages.addAll(received);

        return new ArrayList<>(messages);
    }
}

/**
 * 5.
 * 在聊天软件中，发送方发送消息时，遇到网络超时后就会自动重发，因此，接收方可能会收到重复的消息，
 * 在显示给用户看的时候，需要首先去重。请练习使用Set去除重复的消息：
 */
class Message {
    public final int sequence;
    public final String text;
    public Message(int sequence, String text) {
        this.sequence = sequence;
        this.text = text;
    }
}

