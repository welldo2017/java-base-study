package com.welldo.zero.a15_thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Atomic
 *
 * todo 没懂, 后面再研究
 *
 * @author welldo
 * @date 2020/9/9
 */
public class Atomic_6 {

    public int incrementAndGet(AtomicInteger var) {
        int prev, next;
        do {
            prev = var.get();
            next = prev + 1;
        } while ( ! var.compareAndSet(prev, next));
        return next;
    }
}
