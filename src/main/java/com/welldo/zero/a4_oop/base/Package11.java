package com.welldo.zero.a4_oop.base;

/**
 * 包
 *
 * 一个类总是属于某个包，类名（比如Person）只是一个简写，真正的完整类名是"包名.类名" .
 * 在Java虚拟机执行的时候，JVM只看完整类名，因此，只要包名不同，类就不同。
 *
 *
 * 3. import
 * 在写import的时候，可以使用*，表示把这个包下面的所有class都导入进来（但不包括子包的class）：
 * 所以, 默认自动 import java.lang.*。但类似 java.lang.reflect 这些包仍需要手动导入。
 *
 * 4.
 * 编译器会自动帮我们做两个import动作：
 * 默认自动import当前package的其他class；
 * 默认自动import java.lang.*。
 *
 * @author welldo
 * @date 2020/8/9
 */
public class Package11 {
    public static void main(String[] args) {

        //不用导包,直接new,原因是 4.
        Person11 p = new Person11();

        // p.hello1(); // 不可以调用
        p.hello2(); // 可以调用，因为 Package11 和 Person11 在同一个包
    }
}


