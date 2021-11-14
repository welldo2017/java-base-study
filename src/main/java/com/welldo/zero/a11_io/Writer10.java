package com.welldo.zero.a11_io;

import java.io.*;

/**
 * Writer
 *
 * Reader是带编码转换器的InputStream，它把byte转换为char，
 * 而Writer就是带编码转换器的OutputStream，它把char转换为byte并输出。
 *
 * 0. 区别
 * +────────────────────────+────────────────────+
 * | outputStream            | writer             |
 * +========================+=====================+
 * | 字节流 byte单位          | 字符流 char单位       |
 * | 写入字节(0~255)          | 写入字符(0~65535)    |
 * | void write(int b)      | void write(int b)   |
 * +────────────────────────+────────────────────+
 *
 *
 * 1.FileWriter
 * FileWriter就是向文件中写入字符流的Writer。它的使用方法和FileReader类似：
 *
 *
 * 2.CharArrayWriter (写往内存)
 * CharArrayWriter可以在内存中创建一个Writer，它的作用实际上是构造一个缓冲区，可以写入char，最后得到写入的char[]数组，
 * 这和ByteArrayOutputStream非常类似：
 *
 * 3.
 * StringWriter (写往string)
 * StringWriter也是一个基于内存的Writer，它和CharArrayWriter类似
 *
 *
 * 4.OutputStreamWriter
 * 除了CharArrayWriter 和 StringWriter外，普通的Writer实际上是基于OutputStream构造的，
 * 它接收char，然后在内部自动转换成一个或多个byte，并写入OutputStream。
 * 因此，OutputStreamWriter就是一个将任意的OutputStream转换为Writer的转换器：
 *
 *
 *
 * @author welldo
 * @date 2020/8/28
 */
public class Writer10 {
    public static void main(String[] args) throws IOException {

        // new Writer10().write1();
        new Writer10().write23();
        new Writer10().write4();
    }


    /**
     * 4. todo 写往哪里?
     */
    public void write4() throws IOException {
        try (Writer writer = new OutputStreamWriter(new FileOutputStream("io/fromWriter1.txt"), "UTF-8")) {
            //只能打印内存地址, 不能打印实际内容.
            System.out.println(writer.toString());
        }
    }


    /**
     * 2 & 3
     */
    public void write23() throws IOException {
        try (CharArrayWriter writer = new CharArrayWriter()) {
            writer.write(65);
            writer.write(66);
            writer.write(67);

            char[] data = writer.toCharArray(); // { 'A', 'B', 'C' }
            System.out.println(data);
        }
        try (StringWriter writer = new StringWriter()){
            writer.write(65);
            writer.write(66);
            writer.write(67);

            String s = writer.toString();
            System.out.println(s);
        }
    }


    //1.
    public void write1() throws IOException {
        //编码集,为系统默认的编码集
        try (Writer writer = new FileWriter("io/fromWriter1.txt")) {
            writer.write('H'); // 写入单个字符
            writer.write("Hello".toCharArray()); // 写入char[]
            writer.write("Hello"); // 写入String
        }
    }

}
