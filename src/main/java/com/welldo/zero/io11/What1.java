package com.welldo.zero.io11;

import com.welldo.zero.oop_4.core_class.StrAndEncode2;

/**
 * IO是指Input/Output，即输入和输出。
 *
 * 1. 以内存为中心：
 * Input指从外部读入数据到内存，例如，把文件从磁盘读取到内存，从网络读取数据到内存等等。
 * 并且以Java提供的某种数据类型表示，例如，byte[]，String，这样，后续代码才能处理这些数据。
 *
 * Output指把数据从内存输出到外部，例如，把数据从内存写入到文件，把数据从内存输出到网络等等。
 *
 * 2.
 * 为什么要把数据读到内存才能处理这些数据？因为代码是在内存中运行的，数据也必须读到内存
 *
 *
 * 3.
 * IO流是一种顺序读写数据的模式，它的特点是单向流动。数据类似自来水一样在水管中流动，所以我们把它称为IO流。且先进先出
 *
 * 4.InputStream / OutputStream
 * IO流以byte（字节）为最小单位，因此也称为字节流
 *
 *
 * 5.Reader / Writer     * {@link StrAndEncode2}
 * 如果我们需要读写的是字符，并且字符不全是单字节表示的ASCII字符，那么，按照char来读写显然更方便，这种流称为字符流。
 * Java提供了Reader和Writer表示字符流，字符流传输的最小数据单位是char。
 *
 * 例如，我们把char[]数组  这4个字符用Writer字符流写入文件，并且使用UTF-8编码，
 * 得到的最终文件内容是8个字节，英文字符H和i各占一个字节，中文字符你好各占3个字节： *
 * 0x48 * 0x69 * 0xe4bda0 * 0xe5a5bd
 * 反过来，我们用Reader读取以UTF-8编码的这8个字节，会从Reader中得到 "Hi你好" 这4个字符。
 *
 * 因此，Reader和Writer本质上是一个能自动编解码的InputStream和OutputStream。
 * 使用Reader，数据源虽然是字节，但我们读入的数据都是char类型的字符，原因是Reader内部把读入的byte做了处理，转换成了char。
 *
 * 使用InputStream，我们读入的数据和原始二进制数据一模一样，是byte[]数组，但是我们可以自己把二进制byte[]数组按照某种编码转换为字符串。
 * 究竟使用Reader还是InputStream，要取决于具体的使用场景。
 * 如果数据源不是文本，就只能使用InputStream，
 * 如果数据源是文本，使用Reader更方便一些。Writer和OutputStream是类似的。
 *
 *
 * 6.小结
 * IO流是一种流式的数据输入/输出模型：
 *      二进制数据以byte为最小单位在InputStream/OutputStream中单向流动；
 *      字符数据以char为最小单位在Reader/Writer中单向流动。
 *
 * Java标准库的包java.io提供了同步IO，而java.nio则是异步IO。
 *      字节流接口：InputStream/OutputStream；
 *      字符流接口：Reader/Writer。
 *
 * @author welldo
 * @date 2020/8/24
 */
public class What1 {
}
