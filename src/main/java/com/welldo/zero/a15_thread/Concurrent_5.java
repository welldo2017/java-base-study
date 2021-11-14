package com.welldo.zero.a15_thread;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Concurrent集合
 *
 * 0.
 * 我们在前面已经通过ReentrantLock和Condition实现了一个BlockingQueue：{@link TaskQueue2}
 * BlockingQueue的意思就是说，
 * 当一个线程调用这个TaskQueue的getTask()方法时，该方法内部可能会让线程变成等待状态，
 * 直到队列条件满足不为空，线程被唤醒后，getTask()方法才会返回。
 *
 * 因为BlockingQueue非常有用，所以我们不必自己编写，
 * 可以直接使用Java标准库的java.util.concurrent包提供的线程安全的集合：ArrayBlockingQueue。
 *
 * 1.java.util.concurrent包也提供了对应的并发集合类。我们归纳一下：
 * ------------------------------------------------------------------------
 * interface	non-thread-safe     	    thread-safe
 * ========================================================================
 * List	        ArrayList	                CopyOnWriteArrayList
 * Map	        HashMap	                    ConcurrentHashMap
 * Set	        HashSet / TreeSet	        CopyOnWriteArraySet
 * Queue	    ArrayDeque / LinkedList	    ArrayBlockingQueue / LinkedBlockingQueue
 * Deque	    ArrayDeque / LinkedList	    LinkedBlockingDeque
 * ------------------------------------------------------------------------
 *
 * 2.使用这些并发集合与使用非线程安全的集合类完全相同
 * 因为所有的同步和加锁的逻辑都在集合内部实现，对程序员来说，只需要正常按接口引用
 *
 * 3. java.util.Collections工具类还提供了一个旧的线程安全集合转换器
 * 它实际上是用一个包装类包装了非线程安全的Map，然后对所有读写方法都用synchronized加锁，
 * !!! 这样获得的线程安全集合的性能比java.util.concurrent集合要低很多，所以不推荐使用。
 *
 * @author welldo
 * @date 2020/9/9
 */
public class Concurrent_5 {
    public static void main(String[] args) {
        //2.在不同的线程读写:
        Map<String, String> map = new ConcurrentHashMap<>();

        map.put("A", "1");
        map.put("B", "2");
        map.get("A");

        //3.
        Map unsafeMap = new HashMap();
        Map threadSafeMap = Collections.synchronizedMap(unsafeMap);
    }
}
