package com.welldo.zero.io11;

import java.io.ByteArrayInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Filter模式 (装饰器模式)
 *
 * 0.
 * Java的IO标准库提供的 InputStream 根据来源可以包括：
 *      FileInputStream：从文件读取数据，是最终数据源；
 *      ServletInputStream：从HTTP请求读取数据，是最终数据源；
 *      Socket.getInputStream()：从TCP连接读取数据，是最终数据源；
 *      ...
 *
 *
 *
 *
 * 1. 痛点
 * 如果我们要给FileInputStream添加 A 功能，则可以从FileInputStream派生一个类：
 * aFileInputStream extends FileInputStream
 *
 * 如果要给FileInputStream添加 B 功能，可以从FileInputStream派生一个类：
 * bFileInputStream extends FileInputStream
 *
 * 如果要给FileInputStream添加 c 功能，还是可以从FileInputStream派生一个类：
 * cFileInputStream extends FileInputStream
 * ....
 *
 * 如果要给FileInputStream添加a功能和b功能，还需要派生 abFileInputStream。
 * 如果要给FileInputStream添加a功能和c功能，则需要派生 acFileInputStream。
 *
 * 我们发现，给FileInputStream添加3种功能，至少需要3个子类。这3种功能的组合，又需要更多的子类：
 * 这就是 子类数量爆炸
 *                           ┌─────────────────┐
 *                           │ FileInputStream │
 *                           └─────────────────┘
 *                                    ▲
 *              ┌───────────┬─────────┼─────────┬───────────┐
 *              │           │         │         │           │
 * ┌───────────────────────┐│┌─────────────────┐│┌─────────────────────┐
 * │BufferedFileInputStream│││DigestInputStream│││CipherFileInputStream│
 * └───────────────────────┘│└─────────────────┘│└─────────────────────┘
 *                          │                   │
 *     ┌─────────────────────────────┐ ┌─────────────────────────────┐
 *     │BufferedDigestFileInputStream│ │BufferedCipherFileInputStream│
 *     └─────────────────────────────┘ └─────────────────────────────┘
 *
 * 因此，直接使用继承，为各种InputStream附加更多的功能，根本无法控制代码的复杂度，很快就会失控。
 *
 *
 *
 *
 *
 *
 *
 * 2.解决方案
 * 为了解决依赖继承会导致子类数量失控的问题，JDK首先将InputStream分为两大类：
 *
 * 一类是直接提供数据的基础InputStream，例如：
 *      FileInputStream
 *      ByteArrayInputStream
 *      ServletInputStream
 *      ...
 *
 * 一类是提供其他功能的InputStream，例如：
 *      BufferedInputStream(缓冲)
 *      DigestInputStream (计算签名)
 *      CipherInputStream (加密/解密)
 *      ...
 *
 * step1. 当我们需要给一个“基础”InputStream附加各种功能时，如 FileInputStream
 * InputStream file = new FileInputStream("test.gz");
 *
 * step2. 紧接着，我们希望给这个FileInputStream能提供缓冲的功能,来提高读取的效率，因此我们用BufferedInputStream包装step1得到的InputStream，
 * 得到的包装类型是BufferedInputStream, 使用多态,赋值给InputStream：
 * InputStream buffered = new BufferedInputStream(file);
 *
 * step3. 最后，假设该文件已经用gzip压缩了，我们希望直接读取解压缩的内容，就可以再包装step2得到的文件,变成一个GZIPInputStream：
 * InputStream gzip = new GZIPInputStream(buffered);
 *
 * 无论我们包装多少次，得到的对象始终是InputStream
 * ┌─────────────────────────┐
 * │GZIPInputStream          │
 * │┌───────────────────────┐│
 * ││BufferedFileInputStream││
 * ││┌─────────────────────┐││
 * │││   FileInputStream   │││
 * ││└─────────────────────┘││
 * │└───────────────────────┘│
 * └─────────────────────────┘
 *
 *
 * 3.
 * 上述这种通过一个“基础”组件再叠加各种“附加”功能组件的模式，称之为Filter模式（或者装饰器模式：Decorator）。
 * 它可以让我们通过少量的类来实现各种功能的组合：
 *                  ┌─────────────┐
 *                  │ InputStream │
 *                  └─────────────┘
 *                        ▲ ▲
 * ┌────────────────────┐ │ │ ┌─────────────────┐
 * │  FileInputStream   │─┤ └─│FilterInputStream│
 * └────────────────────┘ │   └─────────────────┘
 * ┌────────────────────┐ │     ▲ ┌───────────────────┐
 * │ByteArrayInputStream│─┤     ├─│BufferedInputStream│
 * └────────────────────┘ │     │ └───────────────────┘
 * ┌────────────────────┐ │     │ ┌───────────────────┐
 * │ ServletInputStream │─┘     ├─│  DataInputStream  │
 * └────────────────────┘       │ └───────────────────┘
 *                              │ ┌───────────────────┐
 *                              └─│CheckedInputStream │
 *                                └───────────────────┘
 *
 *
 * 4.编写FilterInputStream
 * 我们也可以自己编写FilterInputStream，以便可以把自己的FilterInputStream“叠加”到任何一个InputStream中。
 *
 * 5. FilterOutputStream,请参考* {@link PrintStream11}
 *
 * @author welldo
 * @date 2020/8/26
 */
public class Filter6 {
    public static void main(String[] args) throws IOException {

        //4.下面的例子演示了如何编写一个CountInputStream，它的作用是对输入的字节进行计数：
        byte[] data = "hello, world!".getBytes("UTF-8");
        try (CountInputStream input = new CountInputStream(new ByteArrayInputStream(data))) {
            int n;
            while ((n = input.read()) != -1) {
                System.out.println((char)n);
            }
            System.out.println("Total read " + input.getBytesRead() + " bytes");
        }
    }

}

//4.下面的例子演示了如何编写一个CountInputStream，它的作用是对输入的字节进行计数：
class CountInputStream extends FilterInputStream {
    private int count = 0;

    CountInputStream(InputStream in) {
        super(in);
    }

    public int getBytesRead() {
        return this.count;
    }

    public int read() throws IOException {
        int n = in.read();
        if (n != -1) {
            this.count ++;
        }
        return n;
    }

    public int read(byte[] b, int off, int len) throws IOException {
        int n = in.read(b, off, len);
        this.count += n;
        return n;
    }
}

