package com.welldo.zero.a11_io;

import com.welldo.zero.a18_json.WhatIsJson;

import java.io.*;
import java.util.Arrays;

/**
 * 序列化
 *
 * 0.what
 * 序列化是指把一个Java对象变成二进制内容，本质上就是一个byte[]数组。
 *
 * 1.why
 * 序列化后可以把byte[]保存到文件中，或者把byte[]通过网络传输到远程，这样，就相当于把Java对象存储到文件或者通过网络传输出去了。
 * 有序列化，就有反序列化，即把一个二进制内容（也就是byte[]数组）变回Java对象。
 * 有了反序列化，保存到文件中的byte[]数组又可以“变回”Java对象，或者从网络上读取byte[]并把它“变回”Java对象。
 *
 * 2.how
 * 一个Java对象要能序列化，必须实现一个特殊的java.io.Serializable接口(空接口)
 * public interface Serializable { * }
 *
 * 3. 反序列化, 略
 *
 * 4.安全性 & 兼容性
 * 反序列化时，由JVM直接从 byte[]数组 创建构造出Java对象，不调用构造方法. 也就是说构造方法内部的代码(比如校验代码)，在反序列化时根本不可能执行。
 * 因此，它存在一定的安全隐患。一个精心构造的byte[]数组被反序列化后可以执行特定的Java代码，从而导致严重的安全漏洞。
 * 兼容性问题。
 * Java的序列化机制仅适用于Java，如果需要与其它语言交换数据，必须使用通用的序列化方法，例如JSON。{@link WhatIsJson}
 *
 *
 * 其他.
 * Serializable接口没有定义任何方法，它是一个空接口。
 * 我们把这样的空接口称为“标记接口”（Marker Interface），实现了标记接口的类仅仅是给自身贴了个“标记”，并没有增加任何方法。
 *
 * @author welldo
 * @date 2020/8/27
 */
public class Serializable8 {
    public static void main(String[] args) throws IOException {

        //2.把一个Java对象变为byte[]数组，需要使用ObjectOutputStream。它负责把一个Java对象写入一个字节流
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try (ObjectOutputStream output = new ObjectOutputStream(buffer)) {
            // 写入int:
            output.writeInt(12345);
            // 写入String:
            output.writeUTF("Hello");
            // 写入Object:
            output.writeObject(Double.valueOf(123.456));
        }
        System.out.println(Arrays.toString(buffer.toByteArray()));
    }

}
