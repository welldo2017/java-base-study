package com.welldo.zero.a11_io;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * OutputStream
 *
 * 1.OutputStream是Java标准库提供的最基本的输出流。{@link OutputStream}
 *
 * 2.
 * 和InputStream类似，OutputStream也是抽象类，它是所有输出流的超类。
 * 这个抽象类定义的一个最重要的方法就是 void write(int b)，签名如下： void write(int b) ;
 * 这个方法会写入一个字节到输出流。
 * 要注意的是，虽然传入的是int参数，但只会写入一个字节，即只写入int最低8位表示字节的部分（相当于b & 0xff）。
 *
 * 3.
 * OutputStream也提供了close()方法关闭输出流，以便释放系统资源。
 * 要特别注意：OutputStream还提供了一个flush()方法，它的目的是将缓冲区的内容真正输出到目的地。
 *
 * 为什么要有flush()？
 * 因为向磁盘、网络写入数据的时候，出于效率的考虑，操作系统并不是输出一个字节就立刻写入到目的地，
 * 而是把输出的字节先放到内存的一个缓冲区里（本质上就是一个byte[]数组），
 * 等到缓冲区写满了，再一次性写入到目的地。
 * 对于很多IO设备来说，一次写一个字节和一次写1000个字节，花费的时间几乎是完全一样的，所以OutputStream有个flush()方法，能强制把缓冲区内容输出。
 *
 * 通常情况下，我们不需要调用这个flush()方法，因为缓冲区写满了OutputStream会自动调用它，
 * 并且，在调用close()方法关闭OutputStream之前，也会自动调用flush()方法。
 *
 * 在某些情况下，我们必须手动调用flush()方法。举个栗子： * 在线聊天软件
 *
 * 4.缓冲区
 * 实际上，InputStream也有缓冲区。
 * 例如，从FileInputStream读取一个字节时，操作系统往往会一次性读取若干字节到缓冲区，并维护一个指针指向未读的缓冲区。
 * 然后，每次我们调用int read()读取下一个字节时，读取的不是文件中的内容, 而是读取已经保存到缓冲区中的内容的下一个字节，
 * 避免每次读一个字节都导致IO操作。当缓冲区全部读完后继续调用read()，则会触发操作系统的下一次读取并再次填满缓冲区。
 *
 *
 * 5.OutputStream实现类
 * ByteArrayOutputStream可以在内存中模拟一个OutputStream
 *
 *
 * 6.同时读写多个文件的 try写法, 多个资源用分号隔开
 * try (InputStream input = new FileInputStream("input.txt");
 *      OutputStream output = new FileOutputStream("output.txt")) {
 *     input.transferTo(output); // transferTo的作用是?
 * }
 *
 *
 * @author welldo
 * @date 2020/8/26
 */
public class OutputStream4 {
    public static void main(String[] args) throws IOException {
        // new OutputStream4().writeFile2();
        // new OutputStream4().writeFile21();
        // new OutputStream4().writeFile22();
        new OutputStream4().test5();
    }



    /**
     * 5. ByteArrayOutputStream可以在内存中模拟一个OutputStream
     * 它把一个byte[]数组在内存中变成一个OutputStream，虽然实际应用不多，但测试的时候，可以用它来构造一个OutputStream。
     */
    void test5() throws IOException {
        byte[] data;
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            output.write("Hello ".getBytes("UTF-8"));
            output.write("world!".getBytes("UTF-8"));//输出目的地:内存
            data = output.toByteArray();
        }
        System.out.println(new String(data, "UTF-8"));
    }


    /**
     * 22. 为21的捕捉异常优化版
     */
    void writeFile22() throws IOException {
        try (OutputStream output = new FileOutputStream("io/test-out.txt")){
            output.write("hello from 22".getBytes("UTF-8"));
            System.out.println("ok");
        } // 编译器在此自动为我们写入finally并调用close()
    }

    /**
     * 21.每次写入一个字节非常麻烦，更常见的方法是一次性写入若干字节。用OutputStream提供的重载方法void write(byte[])来实现：
     * (这些都是覆盖写)
     */
    void writeFile21() throws IOException {
        OutputStream output = new FileOutputStream("io/test-out.txt");
        output.write("hello from 21".getBytes("UTF-8"));
        output.close();
        System.out.println("ok");
    }

    /**
     * 2.一个字节一个字节写入
     * 方法执行后,检查此文件,看是否成功写入
     * (注意: 1.这些都是覆盖写; 2.文件不存在会自动创建,文件夹不存在则会报错)
     */
    void writeFile2() throws IOException {
        OutputStream output = new FileOutputStream("io/test-out.txt");
        output.write(72); // H
        output.write(101); // e
        output.write(108); // l
        output.write(108); // l
        output.write(111); // o
        output.close();
    }
}
