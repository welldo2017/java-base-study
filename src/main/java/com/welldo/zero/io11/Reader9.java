package com.welldo.zero.io11;

import com.welldo.zero.a4_oop.core_class.StrAndEncode2;

import java.io.*;

/**
 * Reader
 *
 * 0. 区别
 * Reader是Java的IO库提供的另一个输入流抽象类。
 *
 * +────────────────────────+────────────────────+
 * | inputStream            | reader             |
 * +========================+====================+
 * | 字节流 byte单位          | 字符流 char单位       |
 * | 读取字节(-1, 0~255)      | 读取字符(-1, 0~65535)|
 * +────────────────────────+────────────────────+
 *
 * 1.
 * 特别注意!!! reader 并不是接口，而是抽象类 * {@link Reader}
 * 它最主要的方法是：
 * public int read() throws IOException;
 * 这个方法读取字符流的下一个字符，并返回字符表示的int，范围是0~65535。如果已读到末尾，返回-1。
 *
 * 2.FileReader
 * FileReader是Reader的一个子类,它可以打开文件并获取Reader
 *
 *
 * 3.CharArrayReader
 * 它可以在内存中模拟一个Reader，它的作用实际上是把一个char[]数组变成一个Reader，这和ByteArrayInputStream非常类似：
 *
 * 4.StringReader
 * StringReader可以直接把String作为数据源，它和CharArrayReader几乎一样：
 *
 *
 * 5.!!! InputStreamReader
 * Reader和InputStream有什么关系？ 除了特殊的 CharArrayReader 和 StringReader，普通的Reader实际上是基于InputStream构造的(查看源码)，
 * 因为Reader需要从InputStream中读入字节流（byte），然后，根据编码设置，再转换为char就可以实现字符流。
 *
 * 既然Reader本质上是一个基于InputStream的byte到char的转换器，
 * 那么，如果我们已经有一个InputStream，想把它转换为Reader，是完全可行的。
 * InputStreamReader就是这样一个转换器，它可以把任何InputStream转换为Reader。
 *
 *
 * @author welldo
 * @date 2020/8/28
 */
public class Reader9 {
    public static void main(String[] args) throws IOException {
        // new Reader9().readFile2();//测试通过
        // new Reader9().readFile21();//测试通过
        // new Reader9().read3();//测试通过
        // new Reader9().read4();//测试通过
        new Reader9().read5();//测试通过
    }



    /**
     * 5.示例代码如下     * {@link StrAndEncode2}
     */
    public void read5() throws IOException {
        // 持有InputStream:
        InputStream input = new FileInputStream("io/荷塘月色.txt");

        // 变换为Reader:(其实, 就相当于 FileReader )
        Reader reader = new InputStreamReader(input, "UTF-8");//文件用什么编码, 这里就用什么编码读

        char[] buffer = new char[1000];
        int n;
        while ((n = reader.read(buffer)) != -1) {
            System.out.println("read " + n + " chars.");
            System.out.println("*****************");
            System.out.println(new String(buffer,0,n));
        }
    }


    //4.
    public void read4() throws IOException {
        try (Reader reader = new StringReader("Hello4")) {
            int n;
            while ((n = reader.read()) != -1) {
                System.out.println((char) n); // 打印char
            }
        }
    }

    //3.
    public void read3() throws IOException {
        try (Reader reader = new CharArrayReader("Hello3".toCharArray())) {
            int n;
            while ((n = reader.read()) != -1) {
                System.out.println((char) n); // 打印char
            }
        }
    }



    //2.一次性读取若干字符并填充到char[]数组的方法：
    public void readFile21() throws IOException {
        try (Reader reader = new FileReader("io/荷塘月色.txt");) {
            char[] buffer = new char[1000];
            int n;
            while ((n = reader.read(buffer)) != -1) {
                System.out.println("read " + n + " chars.");
                System.out.println(new String(buffer,0,n));
            }
        }
    }

    //2. 一次读取一个字符
    public void readFile2() throws IOException {
        // 创建一个FileReader对象:
        try (Reader reader = new FileReader("io/荷塘月色.txt");) {// 字符编码是???

            for (; ; ) {
                int n = reader.read(); // 反复调用read()方法，直到返回-1
                if (n == -1) {
                    break;
                }
                System.out.print((char) n); // 打印char
            }
        }
    }

}
