package com.welldo.zero.a11_io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * InputStream 和 缓冲
 *
 * 1.
 * InputStream是Java提供的最基本的输入流。{@link InputStream}
 * 特别注意!!! InputStream并不是接口，而是抽象类，它是所有输入流的超类。
 *
 *
 * 2.
 * 这个抽象类定义的一个最重要的方法就是int read()，
 * 这个方法会读取输入流的"下一个字节"，并返回字节表示的int值（0~255）。如果已读到末尾，返回-1表示不能继续读取了。
 * FileInputStream(从文件流中读取数据) 是 InputStream 的一个子类. 看代码.
 *
 *
 * 3.IOException
 * 在读取或写入IO流的过程中，可能会发生错误，例如，文件不存在导致无法读取，没有写权限导致写入失败，等等，
 * 这些底层错误由Java虚拟机自动封装成 IOException 异常并抛出。因此，所有与IO操作相关的代码都必须正确处理IOException。
 * 因此，我们需要用try ... finally来保证InputStream在无论是否发生IO错误的时候都能够正确地关闭.
 *
 *
 * 4.try ... finally 的高级版, 看代码
 *
 *
 * 5.缓冲
 * 在读取流的时候，一次读取一个字节并不是最高效的方法。
 * 很多流支持一次性读取多个字节到缓冲区，对于文件和网络流来说，利用缓冲区一次性读取多个字节效率往往要高很多。
 *
 * InputStream提供了两个重载方法来支持读取多个字节：
 * int read(byte[] b)：读取若干字节并填充到byte[]数组，返回读取的字节数
 * int read(byte[] b, int off, int len)：指定byte[]数组的偏移量和最大填充数
 *
 *
 * 6.ByteArrayInputStream
 *
 * @author welldo
 * @date 2020/8/25
 */
public class InputStream3 {
    public static void main(String[] args) throws IOException {
        InputStream3 test = new InputStream3();
        test.readFile5();

        //6.ByteArrayInputStream实际上是把一个byte[]数组在内存中变成一个InputStream
        // byte[] data = { 72, 101, 108, 108, 111, 33 };
        // try (InputStream input = new ByteArrayInputStream(data)) {
        //     String s = readAsString(input);
        //     System.out.println(s);
        // }
    }


    /**
     * 6.ByteArrayInputStream实际上是把一个byte[]数组在内存中变成一个InputStream，虽然实际应用不多，但可以用它来构造一个InputStream做测试
     */
    public static String readAsString(InputStream input) throws IOException {
        int n;
        StringBuilder sb = new StringBuilder();
        while ((n = input.read()) != -1) {
            sb.append((char) n);
        }
        return sb.toString();
    }


    /**
     * 5. 缓冲
     * 需要先定义一个byte[]数组作为缓冲区，read()方法会尽可能多地读取字节到缓冲区, 但不会超过缓冲区的大小。
     * read()方法的返回值不再是字节的int值，而是返回实际读取了多少个字节。
     * 如果返回-1，表示没有更多的数据了。
     */
    public void readFile5() throws IOException {

        try (InputStream input = new FileInputStream("readme.md")) {//此文件有930个字节
            // 定义400个字节大小的缓冲区:
            byte[] buffer = new byte[400];
            int n;
            while ((n = input.read(buffer)) != -1) { // 读取到缓冲区
                System.out.println("read " + n + " bytes.");

                //输出为string 由于缓冲区大小为n,读取到这里,有的字符难免被截断, 如果输出,可能会乱码
                System.out.println(new String(buffer,0,n));
            }
        }
    }


    /**
     * 4.
     * 用try ... finally来编写上述代码会感觉比较复杂，
     * 更好的写法是利用Java 7引入的新的try(resource)的语法，只需要编写try语句，让编译器自动为我们关闭资源。
     * 推荐的写法如下：
     *
     * 实际上，编译器并不会特别地为InputStream加上自动关闭。
     * 编译器只看try(resource = ...)中的对象是否实现了java.lang.AutoCloseable接口，如果实现了，就自动加上finally语句并调用close()方法。
     * {@link AutoCloseable}
     */
    public void readFile4() throws IOException {
        try (InputStream input = new FileInputStream("readme.md")) {
            int n;
            while ((n = input.read()) != -1) {
                System.out.println(n);
            }
        } // 编译器在此自动为我们写入finally并调用close()
    }

    /**
     * 2.
     * 在计算机中，文件、网络端口这些资源，都是由操作系统统一管理的。
     * 应用程序在运行的过程中，如果打开了一个文件进行读写，完成后要及时地关闭，以便让操作系统把资源释放掉，
     * 否则，应用程序占用的资源会越来越多，不但白白占用内存，还会影响其他应用程序的运行。
     *
     * 关闭流就会释放对应的底层资源。
     */
    public void readFile() throws IOException {
        InputStream input = null;
        try {
            input = new FileInputStream("src/readme.txt");
            int n;
            // 反复调用read()方法，直到返回-1    // 利用while同时读取并判断
            while ((n = input.read()) != -1) {
                System.out.println(n);// 打印byte的值
            }
        } finally {
            if (input != null) {
                input.close();
            }
        }
    }


}
