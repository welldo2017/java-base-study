package com.welldo.zero.collection10;

import com.welldo.zero.io11.Classpath7;

import java.io.*;
import java.util.Properties;

/**
 * 使用Properties
 *
 * 1.
 * 配置文件的特点是，它的Key-Value一般都是String-String类型的，因此我们完全可以用Map<String, String>来表示它。
 *
 * 因为配置文件非常常用，所以Java集合库提供了一个Properties来表示一组“配置”。
 * 由于历史遗留原因，Properties内部本质上是一个Hashtable，但我们只需要用到Properties自身关于读写配置的接口。
 *
 * 2.
 * Java默认配置文件以.properties为扩展名，每行以key=value表示，以#号开头的行,是注释。以下是一个典型的配置文件：
 * # setting.properties
 * last_open_file=/data/hello.txt
 * auto_save_interval=60
 *
 * 3.读取 * 一共有三步：
 * 创建Properties实例 prop；
 * prop调用load(InputStream)读取文件；
 * 调用getProperty()获取配置。如果key不存在，将返回null。我们还可以提供一个默认值，这样，当key不存在的时候，就返回默认值。
 *
 *
 * 4.
 * 如果有多个.properties文件，可以反复调用load()读取，后读取的key-value会覆盖已读取的key-value：
 * Properties props = new Properties();
 * props.load(getClass().getResourceAsStream("/setting.properties"));
 * props.load(new FileInputStream("C:\\conf\\setting.properties"));
 *
 *
 * 5.
 * load(InputStream)读取文件；
 * load(InputStream)方法接收一个InputStream 实例，表示一个字节流 / 文件流 / 资源流
 *
 * 6.写入配置文件
 * props.setProperty("language", "Java");
 * props.store(new FileOutputStream("C:\\conf\\setting.properties"), "这是写入的properties注释");
 *
 *
 * 7.编码
 * 早期版本的Java规定.properties文件编码是ASCII编码（ISO8859-1），
 * 如果涉及到中文就必须用name=\u4e2d\u6587来表示，非常别扭。从JDK9开始，*.properties文件可以使用UTF-8编码了。
 *
 * 不过，需要注意的是，由于load(InputStream)默认总是以ASCII编码读取字节流，所以会导致读到乱码。
 * 我们需要用另一个重载方法load(Reader)读取：
 * props.load(new FileReader("settings.properties", StandardCharsets.UTF_8));
 *
 *
 *
 *
 * @author welldo
 * @date 2020/8/21
 */
public class Properties9 {
    public static void main(String[] args) throws IOException {


        //3.从文件系统读取这个.properties文件：
        String propName = "D:\\ideaSpace\\welldo-study\\java-study\\src\\main\\resources\\setting.properties";
        Properties prop3 = new Properties();
        prop3.load(new FileInputStream(propName));
        System.out.println(prop3.getProperty("last_open_file"));
        System.out.println(prop3.getProperty("auto_save_interval"));


        /**
         * 31.从classpath读取.properties文件; 详情请参考 {@link Classpath7}
         */
        Properties prop31 = new Properties();
        prop31.load(Properties9.class.getResourceAsStream("/setting.properties"));
        System.out.println(prop31.getProperty("last_open_file"));
        System.out.println(prop31.getProperty("auto_save_interval"));


        //4.

        //5.接收字节流, 文件流 , 资源流
        String settings = "# test" + "\n" + "course=Java" + "\n" + "last_open_date=2019-08-07T12:35:01";
        ByteArrayInputStream input = new ByteArrayInputStream(settings.getBytes("UTF-8"));
        Properties props5 = new Properties();
        props5.load(input);
        System.out.println("course: " + props5.getProperty("course"));
        System.out.println("last_open_date: " + props5.getProperty("last_open_date"));
        System.out.println("last_open_file: " + props5.getProperty("last_open_file"));
        System.out.println("auto_save: " + props5.getProperty("auto_save", "60"));

        //6.写入
        prop3.setProperty("writeTime","2020-08-24T12:35:01");
        prop3.store(new FileOutputStream(propName),"test-wirte");
        System.out.println("写入成功");

    }
}
