package com.welldo.zero.a11_io;

import com.welldo.zero.a10_collection.Properties9;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取classpath资源
 * classpath是JVM用到的一个环境变量，它用来指示JVM如何搜索class文件,所以叫class path。
 *
 * 1. {@link Properties9}
 * 很多Java程序启动的时候，都需要读取配置文件。例如，从一个.properties文件中读取配置：
 * String conf = "C:\\conf\\default.properties";
 * try (InputStream input = new FileInputStream(conf)) {
 *      //xxxxxx
 * }
 *
 * 这段代码要正常执行，必须在C盘创建conf目录，然后在目录里创建default.properties文件。但是，在Linux系统上，路径和Windows的又不一样。
 * 因此，从磁盘的固定目录读取配置文件，不是一个好的办法。 * 有没有路径无关的读取文件的方式呢？
 *
 *
 * 2.
 * 我们知道，Java存放.class的目录,也可以包含任意其他类型的文件，例如.properties / .jpg / .txt / .csv
 * 在classpath中的资源文件，路径总是以／开头(所以,从classpath读取文件就可以避免不同环境下,文件路径不一致的问题)
 * 我们先获取当前的Class对象，然后调用getResourceAsStream()就可以直接从 classpath 读取任意的资源文件：
 *
 * 2.5 一定要和 "当前目录" 分清楚, 详情请参考 {@link FileObject2} 的注释
 *
 *
 * 3.
 * 如果我们把默认的配置放到jar包中，再从外部文件系统读取一个可选的配置文件，就可以做到既有默认的配置文件，又可以让用户自己修改配置：
 * Properties props = new Properties();
 * props.load(inputStreamFromClassPath("/default.properties"));
 * props.load(inputStreamFromFile("./conf.properties"));
 * 这样读取配置文件，应用程序启动就更加灵活。
 *
 * @author welldo
 * @date 2020/8/27
 */
public class Classpath7 {
    public static void main(String[] args) throws IOException {
        //2.
        Properties prop = new Properties();

        try (InputStream input = Classpath7.class.getResourceAsStream("/setting.properties")) {
            if (input != null) {
                System.out.println("yes");
                prop.load(input);
                System.out.println(prop.getProperty("last_open_file"));

            }else {
                System.out.println("no");
            }
        }
    }
}
