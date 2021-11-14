package com.welldo.zero.a11_io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 小作业: 请利用InputStream和OutputStream，编写一个复制文件的程序,它可以带参数运行：
 *
 * 方法1.
 * Java 9在InputStream中增加了transferTo方法
 *  input.transferTo(output) * 从该输入流中读取所有字节，并按照读取的顺序将字节写入给定的输出流
 *
 *
 * 方法2:readAllBytes, 全部读出
 *
 * 方法3: 常规方法, 部分读出
 *
 * @author welldo
 * @date 2020/8/26
 */
public class InputOutput5 {

    public static void main(String[] args) throws IOException {
        String sourceFileName = "io/荷塘月色.txt";
        String targetFileName = "io/荷塘月色-copy.txt";


        // copy(sourceFileName,targetFileName);//测试通过
        copy3(sourceFileName,targetFileName);//测试通过
    }


    /**
     * 3. 常规方法
     * todo 处理字符集
     */
    public static void copy3(String sourceFileName, String targetFileName) throws IOException {
        try (InputStream input = new FileInputStream(sourceFileName);
             OutputStream output = new FileOutputStream(targetFileName)) {
            byte[] buffer = new byte[1000];
            int n;
            while ((n = input.read(buffer)) != -1) { // 读取到缓冲区
                output.write(buffer,0,n);
            }
        }
    }

    /**
     * 2.
     * 一次性全部读出, 不用手动设计缓冲区的大小
     */
    public static void copy(String sourceFileName, String targetFileName) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(sourceFileName));
        OutputStream output = new FileOutputStream(targetFileName);
        output.write(bytes);
    }



}
